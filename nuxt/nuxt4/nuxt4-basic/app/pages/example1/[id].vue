//@+leo-ver=5-thin
//@+node:swot.20260513084833.1: * @file app/pages/example1/[id].vue
//@@language javascript
//@+<< template [id] >>
//@+node:swot.20260513085035.1: ** << template [id] >>
<template>
<div>
    example id: {{ $route.params.id }}
</div>
</template>
//@-<< template [id] >>
//@+<< script [id] >>
//@+node:swot.20260513085643.1: ** << script [id] >>
<script setup>
const route = useRoute()
console.log("route.params.id:", route.params.id)  // 在js中捕获参数
</script>
//@-<< script [id] >>
//@-leo
