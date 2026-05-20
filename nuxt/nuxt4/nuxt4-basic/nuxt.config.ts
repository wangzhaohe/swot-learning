//@+leo-ver=5-thin
//@+node:swot.20260511103438.1: * @file nuxt.config.ts
//@@language typescript
// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    compatibilityDate: '2025-07-15',
    devtools: { enabled: true },
    // ssr: false,  // nuxt 4.4.5 有bug，降级到 4.4.4 就可以设置为 false
    //@+others
    //@+node:swot.20260511150547.3: ** css 配置全局scss
    // https://nuxt.com/docs/getting-started/styling#the-css-property
    css: ["~/assets/main.scss"],
    //@+node:swot.20260512093120.1: ** modules
    modules: [
        '@element-plus/nuxt',
        //@+<< 配置模块 pinia >>
        //@+node:swot.20260518160505.10: *3* << 配置模块 pinia >>
        // https://prazdevs.github.io/pinia-plugin-persistedstate/frameworks/nuxt
        '@pinia/nuxt',
        'pinia-plugin-persistedstate/nuxt',
        //@-<< 配置模块 pinia >>
    ],
    //@+node:swot.20260512094143.1: ** elementPlus
    elementPlus: { /** Options */ },
    //@-others
})
//@-leo
