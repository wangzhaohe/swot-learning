//@+leo-ver=5-thin
//@+node:swot.20260518091741.1: * @file app/pages/test-auth-middleware.vue
//@@language javascript
//@+<< pages middleware auth >>
//@+node:swot.20260518091814.1: ** << pages middleware auth >>
<script setup>
// 在页面页面中使用局部中间件
definePageMeta({
    middleware: ['auth'],
})
</script>

<template>
<h3>Test 局部中间件</h3>
</template>
//@-<< pages middleware auth >>
//@-leo
