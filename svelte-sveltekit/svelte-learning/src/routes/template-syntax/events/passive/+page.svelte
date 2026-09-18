<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260916083706.1: * @file src/routes/template-syntax/events/passive/+page.svelte-->
<!--@@language html-->
<script>
import { on } from 'svelte/events';

/** @param {HTMLElement} node */
function preventScroll(node) {
    const off = on(node, 'touchmove', (event) => {
            // 如果 passive: true，则下面一行无效，且 console 控制台会报错
            // Unable to preventDefault inside passive event listener invocation
            event.preventDefault();
        }, 
        { passive: false },
        // { passive: true }
    );
    return { destroy: off };
}
</script>

<!-- 给 div 绑定一个 action -->
<div use:preventScroll
     style="height: 200px; overflow-y: auto; border: 1px solid #ccc; padding: 8px;"
>
    用手机触摸这里，然后上下滑动
    {#each Array(30) as _, i}
        <p>第 {i + 1} 行：这是用来撑出滚动条的内容</p>
    {/each}
</div>
<!--@-leo-->
