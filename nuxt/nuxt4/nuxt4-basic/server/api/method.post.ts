//@+leo-ver=5-thin
//@+node:swot.20260520105436.1: * @file server/api/method.post.ts
//@@language javascript
//@+<< api method >>
//@+node:swot.20260520105452.1: ** << api method >>
//@@language typescript
export default defineEventHandler(async (event) => {
    const body = await readBody(event);
    // console.log("body", body);
    return {
        api: "post_method",
        body,
    }
})
//@-<< api method >>
//@-leo
