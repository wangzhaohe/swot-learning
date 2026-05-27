/**
 * 微信小程序授权登录 API
 *
 * POST /api/login
 * Body: { code: string }
 *
 * 流程：
 *   1. 接收前端传来的 wx.login() 获取的临时 code
 *   2. 调用微信 jscode2session 接口换取 openid + session_key
 *   3. 根据 openid 查找或创建用户
 *   4. 签发 JWT token 返回给前端
 *
 * 参考文档：https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html
 */
export default defineEventHandler(async (event) => {
  const body = await readBody(event)

  // 校验必填参数
  if (!body?.code) {
    throw createError({
      statusCode: 400,
      statusMessage: '缺少参数 code'
    })
  }

  // 1. 用 code 换取 openid 和 session_key
  const session = await code2Session(body.code as string)
  console.log('session.unionid', session.unionid)

  // 2. 根据 openid 查找或创建用户（不下发 session_key）
  const user = await findOrCreateUser(session.openid, session.unionid)

  // 3. 签发 JWT token
  const token = await signToken(user.openid, user.unionid)

  // 4. 返回登录态（不包含敏感信息）
  return {
    code: 200,
    message: '登录成功',
    data: {
      token,
      userInfo: {
        openid: user.openid,
        unionid: user.unionid,
        nickname: user.nickname ?? null,
        avatarUrl: user.avatarUrl ?? null,
        isNewUser: !user.nickname // 无昵称视为新用户
      }
    }
  }
})
