//@+leo-ver=5-thin
//@+node:swot.20240101095322.1: * @file server/middleware/logger.ts
//@@language javascript
//@+<< middleware logger >>
//@+node:swot.20240101100533.1: ** << middleware logger >>
//@@language javascript
export default defineEventHandler((event) => {
    // 不包含域名
    console.log('New request1: ' + event.node.req.url);
    // 包含域名
    console.log('New request2: ' + getRequestURL(event));
})
//@-<< middleware logger >>
//@-leo
