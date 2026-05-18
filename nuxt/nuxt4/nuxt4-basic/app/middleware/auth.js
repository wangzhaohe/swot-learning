//@+leo-ver=5-thin
//@+node:swot.20260518091607.1: * @file app/middleware/auth.js
//@@language javascript
//@+<< middleware auth >>
//@+node:swot.20260518091654.1: ** << middleware auth >>
//@@language javascript
export default defineNuxtRouteMiddleware((to, from) => {
    console.log("hello from middleware auth.ts");
})
//@-<< middleware auth >>
//@-leo
