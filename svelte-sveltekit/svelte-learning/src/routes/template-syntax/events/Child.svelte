<script>
	// 对子组件来说，onClickMe 就是一个普通的 prop，只不过它恰好是一个函数。
	let { onClickMe } = $props();

	function handleClick() {
		// 注意：这里的 onclick 是 DOM 事件属性，和上面的 onClickMe prop 是两回事。
		// 拿到父组件传进来的回调后，直接调用它即可“通知”父组件。
		onClickMe?.('hello');
	}
</script>

<button
	class="rounded-lg bg-violet-600 px-4 py-2 text-white shadow-sm transition hover:bg-violet-700 active:scale-95"
	onclick={handleClick}
>
	我是子组件，点我调用 onClickMe 回调
</button>
