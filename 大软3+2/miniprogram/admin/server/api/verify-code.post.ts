/**
 * 验证短信验证码 API
 *
 * POST /api/verify-code
 * Body: { phone: string, code: string }
 *
 * 流程：
 *   1. 接收前端传来的手机号和验证码
 *   2. 从缓存中查找该手机号的验证码
 *   3. 验证验证码是否正确且未过期
 *   4. 验证成功后，查找或创建用户
 *   5. 签发 JWT token
 *   6. 删除缓存中的验证码（一次性使用）
 *   7. 返回 token 和用户信息
 */

// 使用全局变量存储验证码（实际项目中应使用 Redis）
declare global {
  var __codeCache: Map<string, { code: string; expire: number }> | undefined
}

const codeCache = globalThis.__codeCache || (globalThis.__codeCache = new Map())

// Nuxt 3 的 server/utils 目录下的工具函数会自动导入
// 可以直接使用 findOrCreateUser() 和 signToken()

export default defineEventHandler(async (event) => {
  const body = await readBody(event)

  // 1. 校验必填参数
  if (!body?.phone || !body?.code) {
    throw createError({
      statusCode: 400,
      statusMessage: '缺少参数 phone 或 code'
    })
  }

  const phone = body.phone as string
  const code = body.code as string

  // 2. 从缓存中查找验证码
  const cached = codeCache.get(phone)

  // 3. 验证验证码是否存在
  if (!cached) {
    throw createError({
      statusCode: 400,
      statusMessage: '验证码不存在或已过期'
    })
  }

  // 4. 验证验证码是否过期
  if (Date.now() > cached.expire) {
    codeCache.delete(phone) // 删除过期验证码
    throw createError({
      statusCode: 400,
      statusMessage: '验证码已过期'
    })
  }

  // 5. 验证验证码是否正确
  if (cached.code !== code) {
    throw createError({
      statusCode: 400,
      statusMessage: '验证码错误'
    })
  }

  // 6. 验证成功，查找或创建用户（使用手机号作为 openid）
  // 注意：实际项目中应该为短信用户生成独立的用户ID，而不是使用手机号作为 openid
  const user = await findOrCreateUser(phone, undefined)

  // 7. 签发 JWT token
  const token = await signToken(user.openid, user.unionid)

  // 8. 验证成功，删除缓存中的验证码（一次性使用）
  codeCache.delete(phone)

  // 9. 返回 token 和用户信息
  return {
    code: 200,
    message: '登录成功',
    data: {
      token,
      loginType: 'phone',
      userInfo: {
        openid: user.openid,
        unionid: user.unionid,
        nickname: user.nickname ?? null,
        avatarUrl: user.avatarUrl ?? null,
        phone: phone, // 返回手机号
        isNewUser: !user.nickname // 无昵称视为新用户
      }
    }
  }
})
