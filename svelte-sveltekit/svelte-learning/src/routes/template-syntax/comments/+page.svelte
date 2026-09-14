<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260914163002.1: * @file src/routes/template-syntax/comments/+page.svelte-->
<!--@@language html-->
<script>
	import Main from './Main.svelte';
</script>

<div class="space-y-4 p-8">
	<h1 class="text-2xl font-bold">Comments（注释）——@component 文档注释</h1>

	<!-- 1. 普通 HTML 注释：不会渲染到 DOM 里 -->
	<!-- 这是一条普通的注释，页面上看不到它 -->

	<p class="text-sm text-gray-600">
		把鼠标放到下面这行的 <code>Main</code> 上（在编辑器中，比如 VS Code），会弹出
		<code>Main.svelte</code> 里以 <code>@component</code> 开头的注释内容（支持 markdown 和代码块）。
	</p>

	<!-- 2. 使用组件时才看得到悬停提示，页面渲染结果与普通注释一样不受影响 -->
	<Main name="Aretha" />

	<p class="text-sm text-gray-600">
		注意：注释内容不会出现在页面上；同一文件里有多个 <code>@component</code> 注释时，只有最后一个生效。
	</p>
</div>
<!--@-leo-->
