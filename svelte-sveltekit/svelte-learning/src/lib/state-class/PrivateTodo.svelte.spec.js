//@+leo-ver=5-thin
//@+node:swot.20260901105306.1: * @file src/lib/state-class/PrivateTodo.svelte.spec.js
//@@language javascript
import { describe, expect, it } from 'vitest';
import { PrivateTodo } from './placement-rules.svelte.js';
import { createTracker } from './tracker.svelte.js';

// private 字段响应式 / 解构失效（2 个）
// 跑在 vitest 的 client（浏览器）项目里：只有客户端编译产物中 $effect 才会真正执行。

describe('边界情况', () => {
    //@+others
    //@+node:swot.20260904143046.1: ** 1. private 字段 #done = $state(false) 同样具备响应式
    it('private 字段 #done = $state(false) 同样具备响应式', () => {
        const todo = new PrivateTodo('learn svelte');
        const tracker = createTracker(
            () => todo.done
        );

        expect(todo.done).toBe(false);
        expect(tracker.runs).toBe(1);

        tracker.run(() => {
            todo.done = true;
        });

        expect(todo.done).toBe(true);
        expect(tracker.value).toBe(true);
        expect(tracker.runs).toBe(2);
    });
    //@+node:swot.20260904143100.1: ** 2. 解构出来的值只是一份快照，不再具备响应式
    it('解构出来的值只是一份快照，不再具备响应式', () => {
        const todo = new PrivateTodo('learn svelte');
        const { text } = todo;
        const tracker = createTracker(
            () => text
        );

        expect(text).toBe('learn svelte');

        tracker.run(() => {
            todo.text = 'changed';
        });

        // 实例上的字段变了，但之前解构出来的局部变量不会跟着变
        expect(todo.text).toBe('changed');
        expect(text).toBe('learn svelte');
        expect(tracker.runs).toBe(1);
    });
    //@-others
});
//@-leo
