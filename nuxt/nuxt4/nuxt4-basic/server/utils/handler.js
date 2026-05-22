//@+leo-ver=5-thin
//@+node:swot.20260522091448.1: * @file server/utils/handler.js
//@@language javascript
//@+<< utils handler >>
//@+node:swot.20260522091218.1: ** << utils handler >>
//@@language javascript
export const defineWrappedResponseHandler = (handler) =>
    defineEventHandler(async (event) => {
        try {
            // do something before the route handler
            const response = await handler(event);
            console.log('Test server/utils');
            // do something after the route handler
            return { response };
        } catch (err) {
            // Error handling
            return { err };
        }
    });
//@-<< utils handler >>
//@-leo
