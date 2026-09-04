/* global $state */

/**
 * private 字段（`#done = $state(false)`）示例
 * 文档里说 `$state` 可以用在 class 字段上，「无论是 public 还是 private」。
 * 这里验证 private（`#` 私有字段）的写法，并通过 getter / setter 暴露。
 */
export class PrivateTodo {
    #done = $state(false);  // 私有属性

    /** @param {string} text */
    constructor(text) {
        this.text = $state(text);
    }

    get done() { return this.#done; }

    /** @param {boolean} value */
    set done(value) { this.#done = value; }

    reset() {
        this.text = '';
        this.done = false;
    }
}
