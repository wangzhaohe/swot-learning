//@+leo-ver=5-thin
//@+node:swot.20260520104657.1: * @file app/pages/test-query.vue
//@@language javascript
//@+<< test-api-query >>
//@+node:swot.20260520104657.2: ** << test-api-query >>
//@@language typescript
<script setup lang="ts">
const query = await useFetch("/api/query?a=10&b=20&c=30")

// console.log("query", query);
</script>

<template>
    <div>{{query}}</div>
</template>
//@-<< test-api-query >>
//@-leo
