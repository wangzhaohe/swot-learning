//@+leo-ver=5-thin
//@+node:swot.20260901110321.1: * @file src/routes/state-class/page.svelte.spec.js
//@@language javascript
// 注意：不能叫 +page.svelte.spec.js —— SvelteKit 保留 `+` 前缀，会让 svelte-kit sync 直接失败。
// 去掉 + 之后依然匹配 vitest client 项目的 include：`src/**/*.svelte.{test,spec}.{js,ts}`

import { page } from 'vitest/browser';
// describe: 分成组进行测试
// it: 相当于 test，是个测试单元
// expect: 期望的值
import { describe, expect, it } from 'vitest';
import { render } from 'vitest-browser-svelte';
import Page from './+page.svelte';

describe('/state-class 页面', () => {
    //@+others
    //@+node:swot.20260902145135.1: ** 1. 初始渲染出两条待办
    it('初始渲染出两条待办', async () => {
        render(Page);

        await expect.element(page.getByTestId('done-count')).toHaveTextContent('0 / 2');
        await expect.element(page.getByTestId('todo-text-0')).toHaveTextContent('读 Svelte 文档');
        await expect.element(page.getByTestId('todo-text-1')).toHaveTextContent('写验证代码');
    });
    //@+node:swot.20260902145127.1: ** 2. 勾选 checkbox 后计数实时更新（class 字段声明处的 $state 生效）
    it('勾选 checkbox 后计数实时更新（class 字段声明处的 $state 生效）', async () => {
        render(Page);

        await page.getByTestId('todo-done-0').click();
        await expect.element(page.getByTestId('done-count')).toHaveTextContent('1 / 2');
    });
    //@+node:swot.20260902145105.1: ** 3. 编辑输入框会同步更新展示（构造函数里的 $state 生效）
    it('编辑输入框会同步更新展示（构造函数里的 $state 生效）', async () => {
        render(Page);

        await page.getByTestId('todo-input-0').fill('读 Svelte 5 runes 文档');
        await expect
            .element(page.getByTestId('todo-text-0'))
            .toHaveTextContent('读 Svelte 5 runes 文档');
    });
    //@+node:swot.20260902145053.1: ** 4. 点击 reset() 会清空文本并取消勾选
    it('点击 reset() 会清空文本并取消勾选', async () => {
        render(Page);

        await page.getByTestId('todo-done-0').click();
        await page.getByTestId('todo-input-0').fill('临时内容');
        await expect.element(page.getByTestId('done-count')).toHaveTextContent('1 / 2');

        await page.getByTestId('reset-0').click();
        await expect.element(page.getByTestId('todo-input-0')).toHaveValue('');
        await expect.element(page.getByTestId('todo-done-0')).not.toBeChecked();
        await expect.element(page.getByTestId('done-count')).toHaveTextContent('0 / 2');
    });
    //@+node:swot.20260902145027.1: ** 5. 新增待办后仍然具备响应式
    it('新增待办后仍然具备响应式', async () => {
        render(Page);

        await page.getByTestId('draft-input').fill('跑一遍测试');
        await page.getByTestId('add').click();

        await expect.element(page.getByTestId('done-count')).toHaveTextContent('0 / 3');
        await expect.element(page.getByTestId('todo-text-2')).toHaveTextContent('跑一遍测试');

        await page.getByTestId('todo-done-2').click();

        await expect.element(page.getByTestId('done-count')).toHaveTextContent('1 / 3');
    });
    //@-others
});
//@-leo
