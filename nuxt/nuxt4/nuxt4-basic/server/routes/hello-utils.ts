//@+leo-ver=5-thin
//@+node:swot.20260522092909.1: * @file server/routes/hello-utils.ts
//@@language javascript
//@+<< routes hello utils >>
//@+node:swot.20260522092909.2: ** << routes hello utils >>
//@@language javascript
// 使用 defineWrappedResponseHandler，server/utils 下定义的函数才会被执行
export default defineWrappedResponseHandler((event) => {
    return {
        routes: "works",
    }
})
//@-<< routes hello utils >>
//@-leo
