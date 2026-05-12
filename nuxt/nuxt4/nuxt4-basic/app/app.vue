//@+leo-ver=5-thin
//@+node:swot.20260511145756.1: * @file app/app.vue
//@@language javascript
<template lang="pug">
div
    NuxtRouteAnnouncer
    //- NuxtWelcome
    //-
        //@+<< 测试全局 main.scss >>
        //@+node:swot.20260511152359.1: ** << 测试全局 main.scss >>
        //@@language xml
        div Hello Nuxt 3

        //@-<< 测试全局 main.scss >>
        //@+<< 测试 element plus >>
        //@+node:swot.20260512093331.1: ** << 测试 element plus >>
        //@@language pug
        div
            el-button(@click="ElMessage('hello')") button

            ElButton(:icon="ElIconEditPen" type="success") button

            LazyElButton(type="warning") lazy button

            el-icon
                ElIconDeleteFilled

            el-icon
                ElIconBell
        //@-<< 测试 element plus >>
    div
        NuxtPage
</template>
//@-leo
