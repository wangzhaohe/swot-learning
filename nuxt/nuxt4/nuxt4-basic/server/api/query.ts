//@+leo-ver=5-thin
//@+node:swot.20260520104414.1: * @file server/api/query.ts
//@@language javascript
//@+<< api query >>
//@+node:swot.20260520104431.1: ** << api query >>
//@@language typescript
// Example: /api/query?a=10&b=20&c=30

export default defineEventHandler((event) => {
    const query = getQuery(event)
    // 后端处理逻辑，已省略
    return {
        a: query.a,
        b: query.b,
        c: query.c,
    };
});
//@-<< api query >>
//@-leo
