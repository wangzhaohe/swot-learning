<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260917090332.1: * @file src/routes/template-syntax/events/bubbles/+page.svelte-->
<!--@@language html-->
<script>
/** @type {HTMLButtonElement | undefined} bind:this 绑定的目标按钮，挂载前为 undefined */
let targetButton;

/** @param {MouseEvent} event */
function handleClick(event) {
    console.log('handleClick:', event.target);
}

function dispatchClick() {
    // 组件挂载前 targetButton 为 undefined，这里做空值保护
    targetButton?.dispatchEvent(
        new MouseEvent('click', {
            bubbles: true
        })
    );
}
</script>

<button bind:this={targetButton} onclick={handleClick}>
    目标按钮
</button>
<hr>
<button onclick={dispatchClick}>
    手动 dispatch click
</button>
<!--@-leo-->
