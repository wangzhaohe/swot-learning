/**
 * 发送短信验证码 API（模拟）
 *
 * POST /api/send-code
 * Body: { phone: string }
 *
 * 说明：
 *   当前为模拟实现，返回固定验证码 "123456"
 *   实际项目中应调用短信服务商的 API 发送验证码
 *
 * 流程：
 *   1. 接收前端传来的手机号
 *   2. 验证手机号格式
 *   3. 生成验证码（当前返回固定值 "123456"）
 *   4. 调用短信服务商 API 发送验证码（待实现）
 *   5. 返回成功消息
 */

// 使用全局变量存储验证码缓存（实际项目中应使用 Redis）
declare global {
  var __codeCache: Map<string, { code: string; expire: number }> | undefined
}

const codeCache = globalThis.__codeCache || (globalThis.__codeCache = new Map())

export default defineEventHandler(async (event) => {
  const body = await readBody(event)

  // 1. 校验必填参数
  if (!body?.phone) {
    throw createError({
      statusCode: 400,
      statusMessage: '缺少参数 phone'
    })
  }

  const phone = body.phone as string

  // 2. 验证手机号格式
  if (!/^1[3-9]\d{9}$/.test(phone)) {
    throw createError({
      statusCode: 400,
      statusMessage: '手机号格式不正确'
    })
  }

  // 3. 生成验证码（当前返回固定值，实际项目中应随机生成）
  const code = '123456' // 固定验证码，方便测试
  // const code = Math.floor(100000 + Math.random() * 900000).toString() // 随机6位验证码

  // 4. 存储验证码（5分钟有效期）
  const expire = Date.now() + 5 * 60 * 1000 // 5分钟后过期
  codeCache.set(phone, { code, expire })

  // 5. 调用短信服务商 API 发送验证码（待实现）
  // TODO: 接入短信服务商 API（如阿里云、腾讯云等）
  console.log(`[模拟发送] 手机号: ${phone}, 验证码: ${code}`)

  // 6. 返回成功消息
  return {
    code: 200,
    message: '验证码发送成功',
    data: {
      phone,
      // 开发环境下可以返回验证码，方便测试
      // 生产环境必须删除
      code: process.env.NODE_ENV === 'development' ? code : undefined
    }
  }
})
