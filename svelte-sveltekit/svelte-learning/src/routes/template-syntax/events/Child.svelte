<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260915094803.1: * @file src/routes/template-syntax/events/Child.svelte-->
<!--@@language html-->
<script>
    // 对子组件来说，onClickMe 就是一个普通的 prop，只不过它恰好是一个函数。
    let { onClick } = $props();

    function handleClick() {
        // 拿到父组件传进来的回调函数后，直接调用它即可“通知”父组件。
        onClick?.('hello');  // 实际上调用的是函数 handleClickMe
    }
</script>

<button
    class="rounded-lg bg-violet-600 px-4 py-2 text-white shadow-sm transition hover:bg-violet-700 active:scale-95"
    // 注意：这里的 onclick 是 DOM 事件属性，和上面的 onClick prop 是两回事。
    onclick={handleClick}
>
    我是子组件，点我调用 onClick 回调
</button>
<!--@-leo-->
