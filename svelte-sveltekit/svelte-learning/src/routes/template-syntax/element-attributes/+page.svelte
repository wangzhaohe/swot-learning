<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260914142952.1: * @file src/routes/template-syntax/element-attributes/+page.svelte-->
<!--@@language html-->
<script>
    let p = $state(12);
    let disabled = $state(false);
</script>

<a class="text-blue-600 underline"
    // 1. 属性值里能包含 JS 表达式
    href="page/{p}"
>
    <!-- 2. 属性值可以是 JS 表达式 -->
    page {p}
</a>

<div class="mt-4 flex items-center gap-3">
    <!-- {disabled} 等价于 disabled={disabled} -->
    <button
        class="rounded bg-blue-600 px-3 py-1 text-white disabled:bg-gray-400"
        // 3. 属性名和它的值一样，则只写一个花括号包围的 JS 表达式就行了
        {disabled}
    >
        提交
    </button>
    <label class="flex items-center gap-1">
        <input type="checkbox" bind:checked={disabled} />
        测试禁用提交按钮
    </label>
</div>
<!--@-leo-->
