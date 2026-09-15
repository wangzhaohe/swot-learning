<script>
    let count = $state(0);

    function handleClick() {
        count += 1;
    }
</script>

<h1>Events</h1>
<p>
    监听 DOM 事件：给元素添加以 <code>on</code> 开头的属性，例如
    <code>onclick</code>。
</p>

<button onclick={() => console.log('clicked')}>
    点我（仅打印日志）
</button>

<hr />

<button onclick={handleClick}>
    点我计数：{count}
</button>
