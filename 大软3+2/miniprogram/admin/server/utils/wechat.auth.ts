/**
 * 微信小程序登录 - jscode2session 接口封装
 * 文档：https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html
 */

interface WechatSessionResult {
  openid: string
  session_key: string
  unionid?: string
  errcode?: number
  errmsg?: string
}

/**
 * 调用微信 jscode2session 接口，用 code 换取 openid 和 session_key
 */
export async function code2Session(code: string): Promise<WechatSessionResult> {
  const { wechatAppId, wechatSecret } = useRuntimeConfig()

  if (!wechatAppId || !wechatSecret) {
    throw createError({
      statusCode: 500,
      statusMessage: '服务器未配置微信 AppID 或 Secret'
    })
  }

  const url = `https://api.weixin.qq.com/sns/jscode2session?appid=${wechatAppId}&secret=${wechatSecret}&js_code=${code}&grant_type=authorization_code`

  const res = await fetch(url)
  const data = await res.json() as WechatSessionResult

  // 微信接口返回错误
  if (data.errcode) {
    throw createError({
      statusCode: 400,
      statusMessage: `微信登录失败: ${data.errmsg} (errcode: ${data.errcode})`
    })
  }

  if (!data.openid || !data.session_key) {
    throw createError({
      statusCode: 500,
      statusMessage: '微信接口返回数据异常'
    })
  }

  return data
}
