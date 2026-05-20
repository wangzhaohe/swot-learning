//@+leo-ver=5-thin
//@+node:swot.20260520102719.1: * @file server/api/xxx/[name].ts
//@@language javascript
//@+<< api [name] >>
//@+node:swot.20260520102808.1: ** << api [name] >>
//@@language javascript
export default defineEventHandler((event) => {

    const name = getRouterParam(event, 'name')
    return `Hello, ${name}!`

    // 另一种返回方式
    // return `[name]: ${event.context.params!.name}`
})
//@-<< api [name] >>
//@-leo
