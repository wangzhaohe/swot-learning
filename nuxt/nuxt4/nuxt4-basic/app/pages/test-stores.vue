//@+leo-ver=5-thin
//@+node:swot.20260519091456.1: * @file app/pages/test-stores.vue
//@@language javascript
//@+<< use pinia store >>
//@+node:swot.20260518160505.12: ** << use pinia store >>
<script setup>
//@@language typescript
import { useCounterStore } from '~/stores/myStore';

const store = useCounterStore();

</script>

<template>
<div>
    <p>Count: {{store.count}}</p>
    <button @click="store.increment()">
        点我加1
    </button>
</div>
</template>
//@-<< use pinia store >>
//@-leo
