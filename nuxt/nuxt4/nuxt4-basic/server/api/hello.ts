//@+leo-ver=5-thin
//@+node:swot.20260520085633.1: * @file server/api/hello.ts
//@@language javascript
//@+<< api hello >>
//@+node:swot.20260520085659.1: ** << api hello >>
//@@language javascript
export default defineEventHandler((event) => {
  return {
    hello: 'world',
  }
})
//@-<< api hello >>
//@-leo
