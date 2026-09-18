<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260915084807.1: * @file src/routes/template-syntax/events/+page.svelte-->
<!--@@language html-->
<!--@+others-->
<!--@+node:swot.20260915092259.1: ** script-->
<script>
    import Child from './Child.svelte';

    // ── 1. DOM 事件属性：onclick ──
    let count = $state(0);

    function handleClickCount() {
        count += 1;
    }

    // ── 2. 组件事件：其实就是一个回调函数 prop ──
    let message = $state('（还没收到子组件的消息）');

    /**
     * @param {string} payload 子组件调用回调时传回来的数据
     */
    function onClick(payload) {
        message = `父组件收到：${payload}`;
    }
</script>
<!--@+node:swot.20260915092333.1: ** template-->
<div class="mx-auto max-w-2xl space-y-6 p-6">
    <h1 class="text-2xl font-bold text-slate-800">Events</h1>
    <!--@+others-->
    <!--@+node:swot.20260915092556.1: *3* 1. DOM 事件属性 onclick-->
    <section class="space-y-3 rounded-xl border border-slate-200 p-4">
        <h2 class="text-lg font-semibold text-sky-700">1. DOM 事件属性 onclick</h2>

        <p class="text-slate-600">
            给元素添加以 <code class="rounded bg-slate-100 px-1.5 py-0.5 text-sm text-slate-700">on</code
            >
            开头的属性即可监听 DOM 事件，例如
            <code class="rounded bg-slate-100 px-1.5 py-0.5 text-sm text-slate-700">onclick</code>。
        </p>

        <div class="flex flex-wrap gap-3">
            <button
                class="rounded-lg bg-sky-600 px-4 py-2 text-white shadow-sm transition hover:bg-sky-700"
                onclick={() => console.log('clicked')}
            >
                点我（控制台打印 clicked）
            </button>
            <button
                class="rounded-lg bg-emerald-600 px-4 py-2 text-white shadow-sm transition hover:bg-emerald-700"
                onclick={handleClickCount}
            >
                点我计数：{count}
            </button>
        </div>
    </section>
    <!--@+node:swot.20260915092619.1: *3* 2. 组件事件 = 回调函数作为 prop-->
    <section class="space-y-3 rounded-xl border border-slate-200 p-4">
        <h2 class="text-lg font-semibold text-violet-700">
            2. 组件事件 = 回调函数作为 prop
        </h2>

        <p class="text-slate-600">
            <code class="rounded bg-slate-100 px-1.5 py-0.5 text-sm text-slate-700"
                >&lt;Child onClick=&#123;onClick&#125; /&gt;
            </code>
            并不是某种特殊的“事件机制”，本质就是父组件把
            <code class="rounded bg-slate-100 px-1.5 py-0.5 text-sm text-slate-700">
                onClick
            </code>
            这个<strong class="text-slate-800">函数当成普通 prop</strong>
            传给子组件；子组件拿到后<strong class="text-slate-800">直接调用它</strong>来通知父组件。
        </p>
        <!--@+others-->
        <!--@+node:swot.20260915094658.1: *4* 重点代码: 调用子组件传入属性 onClick-->
        <div class="flex flex-wrap items-center gap-3">
            <!-- onClick 就是一个普通的属性 -->
            <!-- 属性和值的完整写法 -->
            <!-- <Child onClick={onClick} /> -->
            <!-- 属性与值相同可以简写 -->
            <Child {onClick} />
            <span class="rounded-lg bg-amber-50 px-3 py-2 text-sm text-amber-700">
                {message}
            </span>
        </div>
        <!--@-others-->
    </section>
    <!--@-others-->
</div>
<!--@-others-->
<!--@-leo-->
