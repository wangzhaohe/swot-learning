//@+leo-ver=5-thin
//@+node:swot.20260914092937.1: * @file src/routes/template-syntax/tags/dot/my.js
/**
 * 命名空间模块。
 *
 * 页面里写 `import * as my from './my.js'` 之后，`my` 就是这个模块导出的对象。
 * 于是模板中可以写 `<my.stuff />`、`<my.thing />`：
 *   点号前面（my）是命名空间，点号后面（stuff / thing）是组件名。
 *
 * 关键点：只要标签里出现点号（或首字母大写），Svelte 就会把它当作“组件”而不是普通 HTML 标签。
 */
export { default as stuff } from './Stuff.svelte';
export { default as thing } from './Thing.svelte';
//@-leo
