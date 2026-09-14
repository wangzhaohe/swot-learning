<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260914163001.1: * @file src/routes/template-syntax/comments/Main.svelte-->
<!--@@language html-->
<!--
@component
组件说明注释（doc comment）：以 `@component` 开头的这段注释不会渲染到页面上，
它只会在**其他文件**里把鼠标悬停在 `<Main>` 组件名上时，作为提示框（tooltip）弹出。

- 这里可以用 markdown 语法，比如 **加粗**、`行内代码`、列表等。
- 这里也可以用代码块。
- Usage（用法）：
  ```html
  <Main name="Aretha">
  ```
-->
<script>
	let { name } = $props();
</script>

<main>
	<h1>
		Hello, {name}
	</h1>
</main>
<!--@-leo-->
