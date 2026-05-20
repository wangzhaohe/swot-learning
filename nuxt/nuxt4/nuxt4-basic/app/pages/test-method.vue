//@+leo-ver=5-thin
//@+node:swot.20260520105706.1: * @file app/pages/test-method.vue
//@@language javascript
//@+<< test-api-method >>
//@+node:swot.20260520105804.1: ** << test-api-method >>
<script setup lang="ts">
//@@language typescript
const post = await useFetch(
    "/api/method",
    {
        method: "post",
        body: {
            name: "Swot",
            age: 48,
        }
    }
)
console.log("post.data.value", post.data.value);
</script>

<template>
<div>
test-api-method
</div>
</template>
//@-<< test-api-method >>
//@-leo
