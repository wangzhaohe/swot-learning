//@+leo-ver=5-thin
//@+node:swot.20260520103505.1: * @file app/pages/test-api-xxx-name.vue
//@@language javascript
//@+<< test-apl-xxx-[name] >>
//@+node:swot.20260520103456.1: ** << test-apl-xxx-[name] >>
//@@language typescript
<script setup lang="ts">
const resxyz = await useFetch("/api/xxx/xyz");
// console.log("resxyz", resxyz);

const resabc = await useFetch("/api/xxx/abc");
// console.log("resabc", resabc);
</script>

<template>
    <div>{{resxyz}}</div>
    <div>{{resabc}}</div>
</template>
//@-<< test-apl-xxx-[name] >>
//@-leo
