//@+leo-ver=5-thin
//@+node:swot.20260515104136.1: * @file app/pages/use-foo.vue
//@@language javascript
//@+<< page use-foo >>
//@+node:swot.20260515104330.1: ** << page use-foo >>
//@@language html
<script setup lang="ts">
const foo = useFoo()
</script>

<template>
  <div>
    {{ foo }}
  </div>
</template>
//@-<< page use-foo >>
//@-leo
