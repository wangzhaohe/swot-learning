//@+leo-ver=5-thin
//@+node:swot.20260519090401.1: * @file app/stores/myStore.ts
//@@language javascript
//@+<< defineStore 组合式 >>
//@+node:swot.20260518160505.11: ** << defineStore 组合式 >>
//@@language javascript
// nuxt4 已经支持自动导入 defineStore、storeToRefs 等等
export const useCounterStore = defineStore(
    'mycounter',
    () => {
        const count = ref(0)
        const name = ref('Swot')
        const doubleCount = computed(
            () => count.value * 2
        )
        function increment() {
            count.value++
        }
        return {
            count,
            name,
            doubleCount,
            increment,  // 函数不能持久化
        }
    },
    {
        // 持久化插件配置
        // IMPORTANT: return 的函数不行，只能是变量才会是持久化。
        persist: true,
    },
)
//@-<< defineStore 组合式 >>
//@-leo
