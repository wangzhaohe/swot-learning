//@+leo-ver=5-thin
//@+node:swot.20260515103756.1: * @file app/composables/useFoo.ts
//@@language javascript
//@+<< composables useFoo >>
//@+node:swot.20260515103821.1: ** << composables useFoo >>
export const useFoo = () => {
  return useState('foo', () => 'bar')
}
//@-<< composables useFoo >>
//@-leo
