<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260914092850.1: * @file src/routes/template-syntax/tags/dot/+page.svelte-->
<!--@@language html-->
<script>
	// 命名空间导入：my 是一个对象，my.stuff / my.thing 是其中的组件
	import * as my from './my.js';
</script>

<div class="space-y-3">
	<h1 class="text-2xl font-bold">点号（命名空间）组件</h1>
	<p class="text-sm text-gray-600">
		点号写法 <code>&lt;my.stuff /&gt;</code> 中，点号前是命名空间，点号后是组件名。小写标签如
		<code>&lt;div&gt;</code> 是普通 HTML 元素，而带点号的标签一定是组件。
	</p>
	<my.stuff />
	<my.thing />
</div>
<!--@-leo-->
