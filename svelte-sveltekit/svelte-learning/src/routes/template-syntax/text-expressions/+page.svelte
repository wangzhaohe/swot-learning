<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260914150800.1: * @file src/routes/template-syntax/text-expressions/+page.svelte-->
<!--@@language html-->
<script>
    let name = $state('world');
    let a = $state(1);
    let b = $state(2);

    // 下面是正则表达式的例子要用的数据
    let value = $state('Svelte');
    let x = '纯字母或空格';
    let y = '包含其他字符';

    // 下面是「转义」和 {@html} 两个例子要用的数据
    let raw = $state('<strong>我是 HTML 片段</strong>');
</script>

<main class="mx-auto max-w-xl space-y-4 p-8">
    <h1 class="text-2xl font-bold">Text expressions（文本表达式）</h1>

    <!-- 1. 花括号里放任意 JS 表达式，结果会作为文本渲染 -->
    <p>Hello {name}!</p>

    <!-- 2. 再例如：花括号里可以是运算、函数调用等任意的 JS 表达式 -->
    <p>{a} + {b} = {a + b}</p>
    <p>{name.toUpperCase()}</p>

    <!-- 3. null / undefined 会被省略，什么也不渲染 -->
    <p>方括号之间都是空的：[{undefined}] 和 [{null}]</p>

    <!-- 4. 其他值（数字、布尔值）会被强制转成字符串 -->
    <p>数字 {a}、布尔值 {true} 都能直接渲染</p>

    <!-- 5. 想显示字面量的花括号，用 HTML 实体 -->
    <p>&#123; 这不是表达式 &#125;</p>

    <!-- 6. 正则字面量（/.../）要加括号，否则会被解析错 -->
    <div>{(/^[A-Za-z ]+$/).test(value) ? x : y}</div>

    <!-- 7. 表达式会被字符串化并转义，防止代码注入：这里显示的是字面文本 这是 svelte 给我们设计好的全量转义 -->
    <p>{raw}</p>

    <!-- 8. 想真正渲染 HTML，用 {@html}，但务必确保内容可信，防止 XSS -->
    <div>{@html raw}</div>
</main>
<!--@-leo-->
