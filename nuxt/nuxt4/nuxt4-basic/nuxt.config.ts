//@+leo-ver=5-thin
//@+node:swot.20260511103438.1: * @file nuxt.config.ts
//@@language typescript
// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    compatibilityDate: '2025-07-15',
    devtools: { enabled: true },
    //@+others
    //@+node:swot.20260511150547.3: ** css 配置全局scss
    // https://nuxt.com/docs/getting-started/styling#the-css-property
    css: ["~/assets/main.scss"],
    //@+node:swot.20260512093120.1: ** modules
    modules: [
        '@element-plus/nuxt'
    ],
    //@+node:swot.20260512094143.1: ** elementPlus
    elementPlus: { /** Options */ },
    //@-others
})
//@-leo
