//@+leo-ver=5-thin
//@+node:swot.20260518162335.1: * @file app/pages/test-useState.vue
//@@language javascript
//@+<< pages test-useState >>
//@+node:swot.20260518162448.1: ** << pages test-useState >>
<template>
    <div>使用前面定义好的 Counter 组件</div>
    <Counter />
</template>

<script setup>
// 在此处也可以直接使用 useState 定义状态变量，如下。
const counter = useCounter();
console.log("counter:", counter);
</script>
//@-<< pages test-useState >>
//@-leo
