//@+leo-ver=5-thin
//@+node:swot.20260515143609.1: * @file app/pages/test-myPlugins.vue
//@@language javascript
//@+<< pages test-myPlugins >>
//@+node:swot.20260515143729.1: ** << pages test-myPlugins >>
//@@language pug
<template lang="pug">
//- 模板中可以直接使用 $hello
div
    h2 test-myPlugins.vue
    div {{ $hello('world') }}
</template>

//@@language typescript
<script setup>
// 也可以在 script 中使用 $hello
const { $hello } = useNuxtApp();
console.log($hello('world'));
</script>
//@-<< pages test-myPlugins >>
//@-leo
