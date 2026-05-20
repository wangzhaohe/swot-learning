//@+leo-ver=5-thin
//@+node:swot.20260520145907.1: * @file server/api/[...slug].ts 
//@@language javascript
//@+<< catch all >>
//@+node:swot.20260520150003.1: ** << catch all >>
export default defineEventHandler((event) => {
    
 console.log('event.context.params.slug to get the route segment:',
              event.context.params.slug);

  return `NotMatch`
})
//@-<< catch all >>
//@-leo
