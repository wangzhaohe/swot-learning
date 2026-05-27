// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2025-07-15',
  devtools: { enabled: true },
  runtimeConfig: {
    wechatAppId: process.env.NUXT_WECHAT_APPID || '',
    wechatSecret: process.env.NUXT_WECHAT_SECRET || '',
    jwtSecret: process.env.NUXT_JWT_SECRET || 'default-jwt-secret-change-me'
  }
})
