<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260901145520.1: * @file src/routes/state-class/+page.svelte-->
<!--@@language html-->
<!--@+others-->
<!--@+node:swot.20260901145520.2: ** script-->
<!--@@language javascript-->
<!--@delims // -->
<script>
//@+others
//@+node:swot.20260901145520.3: *3* 初始化待办数据
import { Todo } from '$lib/state-class/Todo.svelte.js';

/** class 实例组成的数组：数组本身被 proxy，实例本身不会 */
let todos = $state([
    new Todo('读 Svelte 文档'),
    new Todo('写验证代码')
]);

// 用一句话理解：doneCount 是一个"会自动重新计算的数字"，只要跟它相关的数据一变，Svelte 就会自动重算，然后模板里用到它的地方自动更新。
const doneCount = $derived(
    todos.filter((todo) => todo.done).length
);  // 计算 todos 已完成个数

//@+node:swot.20260902104247.1: *3* 新增待办
let draft = $state('');  // 定义新增待办响应式变量

/** @param {SubmitEvent} event */
function addTodo(event) {
    /* 不阻止浏览器的默认行为会发生什么
        浏览器的默认动作是：把表单字段序列化，然后导航到 action 指定的 URL——这里没有 action，就导航到当前页面地址（/state-class?）。
        结果是一次整页刷新，后果是：
            todos、draft 这些 $state 全部被重置，刚加的待办没了；
            地址栏会多出一个 ?；
            页面会闪一下（重新走一次 SSR/ hydration
    */
    event.preventDefault();

    const text = draft.trim();
    if (text === '')  // 没有输入内容则退出函数
        return;

    todos.push(new Todo(text));
    draft = '';
}
//@-others
//@delims <!-- --> 
</script>
<!--@+node:swot.20260901145520.4: ** template-->
<svelte:head>
    <title>$state in class 验证</title>
</svelte:head>

<main class="mx-auto max-w-xl space-y-6 p-8">
    <!--@+others-->
    <!--@+node:swot.20260901170509.1: *3* 已完成计数-->
    <div class="space-y-2">
        <h1 class="text-2xl font-bold">$state 用在 class 里</h1>
        <p class="text-sm text-gray-600">
            已完成
            <strong data-testid="done-count">
                {doneCount} / {todos.length}
            </strong>
        </p>
    </div>
    <!--@+node:swot.20260901170528.1: *3* 添加一条待办-->
    <form onsubmit={addTodo} class="flex gap-2">
        <input
            bind:value={draft}
            data-testid="draft-input"
            placeholder="添加一条待办"
            class="flex-1 rounded border px-3 py-2"
        />
        <button type="submit"
                data-testid="add"
                class="rounded bg-blue-600 px-4 py-2 text-white">
            添加
        </button>
    </form>
    <!--@+node:swot.20260901170643.1: *3* 渲染所有待办-->
    <ul class="space-y-2">
        {#each todos as todo, index (todo)}
            <li class="flex items-center gap-3 rounded border px-3 py-2">
                <!--@+others-->
                <!--@+node:swot.20260901170950.1: *4* 切换是否已完成-->
                <input type="checkbox" 
                       bind:checked={todo.done}
                       data-testid="todo-done-{index}" 
                />
                <!--@+node:swot.20260901171004.1: *4* 更改内容-->
                <input
                    bind:value={todo.text}
                    data-testid="todo-input-{index}"
                    class="flex-1 rounded border px-2 py-1"
                />
                <!--@+node:swot.20260901171019.1: *4* 显示内容-->
                <span
                    data-testid="todo-text-{index}"
                    class:text-gray-400={todo.done}
                    class:line-through={todo.done}
                >
                    {todo.text}
                </span>
                <!--@+node:swot.20260901171033.1: *4* 重置内容-->
                <button
                    type="button"
                    data-testid="reset-{index}"
                    onclick={() => todo.reset()}
                    class="rounded border px-2 py-1 text-sm"
                >
                    reset()
                </button>
                <!--@-others-->
            </li>
        {/each}
    </ul>
    <!--@+node:swot.20260901170811.1: *3* 使用说明-->
    <p class="text-sm text-gray-500">
        勾选 checkbox 或修改文本，上方内容立即变化，说明「字段声明处」和「构造函数里」的
        <code>$state</code> 都生效了。
    </p>
    <!--@-others-->
</main>
<!--@+node:swot.20260901145520.5: ** style-->
<!--@delims /* */ -->
<style>
/*@+others*/
/*@-others*/
/*@delims <!-- --> */
</style>
<!--@-others-->
<!--@+doc-->
<!--
可视化页面 `/state-class`
-->
<!--@-leo-->
