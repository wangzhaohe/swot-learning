//@+leo-ver=5-thin
//@+node:swot.20260518160846.1: * @file app/composables/states.ts
//@@language javascript
//@+<< useState >>
//@+node:swot.20260518160505.3: ** << useState >>
//@@language typescript
export const useCounter = () => useState<number>(
    "counter", () => 0);

// useColor 在本例子中没有用到，只是写了此代码
export const useColor = () => useState<string>(
    "color", () => "pink");
//@-<< useState >>
//@-leo
