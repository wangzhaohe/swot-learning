<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260917103145.1: * @file src/routes/template-syntax/events/on/+page.svelte-->
<!--@@language html-->
<script>
import { on } from 'svelte/events';

/**
 * 直接使用 addEventListener 会阻止 <button> 绑定的委托事件
 * @param {HTMLElement} node
 */
// function action(node) {
//     node.addEventListener('click', (event) => {
//         console.log('manual');
//         event.stopPropagation();
//     });
// }

/**
 * 使用 on() 不会阻止 <button> 绑定的委托事件
 * @param {HTMLElement} node
 */
function action(node) {
    // on() 返回的是移除监听的清理函数，必须包成 { destroy } 才会在元素卸载时被 Svelte 调用
    const off = on(node, 'click', (event) => {
        console.log('manual');
        event.stopPropagation();
    });
    return { destroy: off };
}
</script>

<div use:action>
    <button onclick={() => console.log('svelte')}>
        Click
    </button>
</div>
<!--@-leo-->
