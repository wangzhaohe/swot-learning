//@+leo-ver=5-thin
//@+node:swot.20260515143229.1: * @file app/plugins/myPlugin.ts
//@@language javascript
//@+<< defineNuxtPlugin >>
//@+node:swot.20260515143229.2: ** << defineNuxtPlugin >>
//@@language javascript
export default defineNuxtPlugin(
    nuxtApp => {
        // console.log("nuxtApp", nuxtApp);

        // Automatically Providing Helpers
        return {
            provide: {
                hello: (msg: string) => `Hello ${msg}`
            }
        }
        // 具体使用参下面 test-plugins.vue
    }
);
//@-<< defineNuxtPlugin >>
//@-leo
