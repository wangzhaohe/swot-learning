// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2025-07-15',
  devtools: { enabled: true },
  runtimeConfig: {
    wechatAppId: process.env.NUXT_WECHAT_APPID || '',
    wechatSecret: process.env.NUXT_WECHAT_SECRET || '',
    jwtSecret: process.env.NUXT_JWT_SECRET || 'default-jwt-secret-change-me',
    surrealdb: {
      url: process.env.NUXT_SURREALDB_URL || 'ws://127.0.0.1:8000',
      user: process.env.NUXT_SURREALDB_USER || 'root',
      pass: process.env.NUXT_SURREALDB_PASS || 'root',
      namespace: process.env.NUXT_SURREALDB_NS || 'swzkj',
      database: process.env.NUXT_SURREALDB_DB || 'swzkj'
    }
  }
})
