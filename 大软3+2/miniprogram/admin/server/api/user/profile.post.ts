/**
 * 用户资料更新接口（昵称）
 *
 * POST /api/user/profile
 * Header: Authorization: Bearer <token>
 * Body: { nickname: string }
 *
 * 流程：
 *   1. 鉴权（verifyToken）
 *   2. 校验昵称参数
 *   3. 调用 updateUserProfile 将昵称写入 SurrealDB
 *   4. 返回更新后的用户信息
 */

import { verifyToken } from '../../utils/jwt'
import { updateUserProfile } from '../../utils/user.service'

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

  // 2. 读取并校验昵称
  const body = await readBody(event)
  const nickname = (body?.nickname || '').toString().trim()

  if (!nickname) {
    throw createError({ statusCode: 400, statusMessage: '昵称不能为空' })
  }

  if (nickname.length > 20) {
    throw createError({ statusCode: 400, statusMessage: '昵称不能超过20个字符' })
  }

  // 3. 写入数据库
  const updatedUser = await updateUserProfile(payload.openid, { nickname })
  if (!updatedUser) {
    throw createError({ statusCode: 404, statusMessage: '用户不存在' })
  }

  return {
    code: 200,
    message: '资料更新成功',
    data: {
      openid: updatedUser.openid,
      nickname: updatedUser.nickname,
      avatarUrl: updatedUser.avatarUrl
    }
  }
})
