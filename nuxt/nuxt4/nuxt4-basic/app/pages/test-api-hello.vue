//@+leo-ver=5-thin
//@+node:swot.20260520090126.1: * @file app/pages/test-api-hello.vue
//@@language javascript
//@+<< test-api-hello >>
//@+node:swot.20260520090215.1: ** << test-api-hello >>
//@@language html
<script setup lang="ts">
// 下面注释的条件 ssr: true

// 浏览器不会再发请求
const { data } = await useFetch('/api/hello')

// 浏览器会发请求，所以 $fetch 适合用在事件处理中
const res = await $fetch("/api/hello");

</script>

<template>
  <pre>{{ data }}</pre>
  <pre>{{ res }}</pre>
</template>
//@-<< test-api-hello >>
//@-leo
