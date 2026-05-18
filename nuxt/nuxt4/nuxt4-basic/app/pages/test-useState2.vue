//@+leo-ver=5-thin
//@+node:swot.20260518163855.1: * @file app/pages/test-useState2.vue
//@@language javascript
//@+<< pages test-useState2 >>
//@+node:swot.20260518163855.2: ** << pages test-useState2 >>
<template>
    <div>使用前面定义好的 Counter 组件</div>
    <Counter />
</template>

<script setup>
// 在此处也可以直接使用 useState 定义状态变量，如下。
const counter = useCounter();
console.log("counter:", counter);
</script>
//@-<< pages test-useState2 >>
//@-leo
