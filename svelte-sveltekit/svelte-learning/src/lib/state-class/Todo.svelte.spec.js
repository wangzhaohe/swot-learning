//@+leo-ver=5-thin
//@+node:swot.20260901104750.1: * @file src/lib/state-class/Todo.svelte.spec.js
//@@language javascript
import { describe, expect, it } from 'vitest';
import { Todo } from './Todo.svelte.js';
import { createTracker } from './tracker.svelte.js';

// 单元 + 响应式测试（6 个）
// 注意：这个文件的名字是 *.svelte.spec.js，会跑在 vitest 的 client（浏览器）项目里。
// 原因是 Svelte 在 node/SSR 编译产物中 $effect 不会执行，只有客户端才能真正验证响应式。
// 同理，Todo.svelte.js / tracker.svelte.js 必须带 .svelte.js 后缀，runes 才会被编译。

describe('Todo：把 $state 用在 class 里', () => {
    //@+others
    //@+node:swot.20260903103516.1: ** 1. 字段声明处的 $state(false) 初始值正确
    it('字段声明处的 $state(false) 初始值正确', () => {
        const todo = new Todo('learn svelte');
        expect(todo.done).toBe(false);
    });
    //@+node:swot.20260903103506.1: ** 2. 构造函数首次赋值处的 $state(text) 初始值正确
    it('构造函数首次赋值处的 $state(text) 初始值正确', () => {
        const todo = new Todo('learn svelte');
        expect(todo.text).toBe('learn svelte');
    });
    //@+node:swot.20260903103452.1: ** 3. 修改 done（字段声明式 $state）会触发响应式更新
    it('修改 done（字段声明式 $state）会触发响应式更新', () => {
        const todo = new Todo('learn svelte');
        const tracker = createTracker(
            () => todo.done
        );

        expect(tracker.value).toBe(false);
        expect(tracker.runs).toBe(1);

        tracker.run(() => {
            todo.done = true;
        });

        expect(todo.done).toBe(true);
        expect(tracker.value).toBe(true);
        expect(tracker.runs).toBe(2);
    });
    //@+node:swot.20260903103435.1: ** 4. 修改 text（构造函数式 $state）会触发响应式更新
    it('修改 text（构造函数式 $state）会触发响应式更新', () => {
        const todo = new Todo('learn svelte');
        const tracker = createTracker(
            () => todo.text
        );

        expect(tracker.value).toBe('learn svelte');

        tracker.run(() => {
            todo.text = 'learn runes';
        });

        expect(todo.text).toBe('learn runes');
        expect(tracker.value).toBe('learn runes');
        expect(tracker.runs).toBe(2);
    });
    //@+node:swot.20260903103419.1: ** 5. reset() 同时重置两个字段，并各自触发更新
    it('reset() 同时重置两个字段，并各自触发更新', () => {
        const todo = new Todo('learn svelte');
        const doneTracker = createTracker(() => todo.done);
        const textTracker = createTracker(() => todo.text);

        doneTracker.run(() => {
            todo.done = true;
            todo.text = 'changed';
        });

        expect(doneTracker.value).toBe(true);
        expect(textTracker.value).toBe('changed');
        expect(doneTracker.runs).toBe(2);
        expect(textTracker.runs).toBe(2);

        textTracker.run(
            () => todo.reset()
        );

        expect(todo.text).toBe('');
        expect(todo.done).toBe(false);
        expect(textTracker.value).toBe('');
        expect(doneTracker.value).toBe(false);
        expect(textTracker.runs).toBe(3);
        expect(doneTracker.runs).toBe(3);
    });
    //@+node:swot.20260903103337.1: ** 6. 实例本身没有被 proxy 为响应式：后加的普通属性不具备响应式
    it('实例本身没有被 proxy：后加的普通属性不具备响应式', () => {
        /**
         * note 是后加的普通属性，就是为了测试它不具备响应式
         * @type {Todo & { note?: string }}
         */
        const todo = new Todo('learn svelte');
        const tracker = createTracker(
            () => todo.note
        );

        expect(tracker.runs).toBe(1);

        tracker.run(() => {
            todo.note = 'not reactive';  // 此处可以随便给字符串
        });

        // 值确实写进去了，但 effect 没有重跑 —— 说明实例不是 proxy 的响应式
        expect(todo.note).toBe('not reactive');
        expect(tracker.runs).toBe(1);  // 如果 note 是响应式，就会触发 $effect
    });
    //@-others
});
//@-leo
