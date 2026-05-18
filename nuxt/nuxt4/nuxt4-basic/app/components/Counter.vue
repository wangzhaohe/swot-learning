//@+leo-ver=5-thin
//@+node:swot.20260518161615.1: * @file app/components/Counter.vue
//@@language javascript
//@+<< components Counter >>
//@+node:swot.20260518161645.1: ** << components Counter >>
<template>
    <div>
        Counter: {{ counter }}
        <button @click="counter++">Increment</button>
        <button @click="counter--">Decrement</button>
    </div>
</template>

<script setup>
    const counter = useCounter();
</script>
//@-<< components Counter >>
//@-leo
