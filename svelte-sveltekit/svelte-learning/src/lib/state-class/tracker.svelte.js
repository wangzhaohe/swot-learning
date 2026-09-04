/**
 * https://share.gemini.google/4Do68CR3eWIS gemini 说明该代码
 * runes 只能在 `.svelte` / `.svelte.js` 文件里使用，所以这个辅助函数单独放在 `.svelte.js` 中。
 * tracker.svelte.js = 一个可挂在任意 getter 上的响应式监听器 + 计数器。
 * runs 计数不动,说明被观察的东西没有响应式;
 * runs 计数随修改增加,说明它是货真价实的 $state 响应式属性。
 * 它是你验证 "class 里 $state 用对没有" 的测试利器。
 */

/* global $effect */
import { flushSync } from 'svelte';

/** @param {() => unknown} getter 读取待观察属性的函数 */
export function createTracker(getter) {
    /** @type {unknown} */
    let value = undefined; // 保存最近一次读到的值
    let runs = 0;          // effect 被执行的次数

    // 1. 开启一个不受组件生命周期限制的全局副作用监听
    const destroy = $effect.root(() => {
        $effect(() => {
            value = getter();  // 告诉 $effect 要观察的值，比如 done/text
            runs += 1;         // 只要值变了， effect 就会重跑，次数 +1
        });
    });

    // 2. 强制立即同步执行一次，确保初始状态下的 value 和 runs 有值 (runs 变成 1)
    flushSync();

    return {
        /** effect 最近一次读到的值 */
        get value() {
            return value;
        },
        /** effect 被执行了多少次 */
        get runs() {
            return runs;
        },
        /**
         * 修改状态并同步 flush，确保 effect 已经重跑
         * @param {() => void} mutate
         */
        run(mutate) {
            flushSync(mutate);
        },
        destroy
    };
}
