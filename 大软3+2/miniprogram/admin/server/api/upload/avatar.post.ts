/**
 * 头像上传接口
 *
 * POST /api/upload/avatar
 * Header: Authorization: Bearer <token>
 * Body: form-data, file field = "avatar"
 *
 * 流程：
 *   1. 鉴权（verifyToken）
 *   2. 校验文件类型和大小
 *   3. 保存文件到 public/avatars/ 目录
 *   4. 调用 updateUserProfile 将永久头像 URL 写入 SurrealDB
 *   5. 返回可访问的头像 URL
 */

import { verifyToken } from '../../utils/jwt'
import { updateUserProfile } from '../../utils/user.service'
import { mkdir, writeFile } from 'node:fs/promises'
import { resolve } from 'node:path'

export default defineEventHandler(async (event) => {
  // 1. 鉴权
  const authHeader = getHeader(event, 'authorization') || ''
  const token = authHeader.replace(/^Bearer\s+/i, '')
  if (!token) {
    throw createError({ statusCode: 401, statusMessage: '未登录' })
  }

  let payload: any
  try {
    payload = await verifyToken(token)
  } catch {
    throw createError({ statusCode: 401, statusMessage: 'Token 无效或已过期' })
  }

  // 2. 读取上传的文件
  const formData = await readFormData(event)
  const file = formData.get('avatar') as File | null

  if (!file || file.size === 0) {
    throw createError({ statusCode: 400, statusMessage: '未检测到头像文件' })
  }

  // 3. 校验文件类型
  const allowedTypes = ['image/jpeg', 'image/png', 'image/gif', 'image/webp']
  if (!allowedTypes.includes(file.type)) {
    throw createError({ statusCode: 400, statusMessage: '仅支持 JPG/PNG/GIF/WEBP 格式' })
  }

  // 4. 校验文件大小（限制 2MB）
  const maxSize = 2 * 1024 * 1024
  if (file.size > maxSize) {
    throw createError({ statusCode: 400, statusMessage: '头像文件不能超过 2MB' })
  }

  // 5. 生成唯一文件名并保存
  const ext = file.type.replace('image/', '') || 'jpg'
  const filename = `avatar_${payload.openid}_${Date.now()}.${ext}`
  const publicDir = resolve(process.cwd(), 'public', 'avatars')
  await mkdir(publicDir, { recursive: true })

  const filepath = resolve(publicDir, filename)
  const arrayBuffer = await file.arrayBuffer()
  await writeFile(filepath, new Uint8Array(arrayBuffer))

  // 6. 构建可访问的 URL
  const baseUrl = getRequestURL(event).origin
  const avatarUrl = `${baseUrl}/avatars/${filename}`

  // 7. 写入数据库
  await updateUserProfile(payload.openid, { avatarUrl })

  return {
    code: 200,
    message: '头像上传成功',
    data: { avatarUrl }
  }
})
