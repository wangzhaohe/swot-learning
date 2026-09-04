/* global $state */

/**
 * 验证 https://svelte.dev/docs/svelte/$state#Classes 中的写法。
 *
 * 官方说明：class 实例本身**不会**被 proxy 代理，但可以分别在
 *  1. class 字段声明处（public / private 都可）
 *  2. 构造函数里对某个属性的**首次**赋值处
 * 使用 `$state`，从而让单个属性具备响应式。
 *
 * 注意：文件名必须是 `*.svelte.js`，模块里的 runes 才会被编译。
 */
export class Todo {
    /** 字段声明处使用 $state */
    done = $state(false);

    /**
     * @param {string} text
     */
    constructor(text) {
        /** 构造函数中对该属性的首次赋值处使用 $state */
        this.text = $state(text);
    }

    /** 把两个响应式字段一起重置 */
    reset() {
        this.text = '';
        this.done = false;
    }
}
