//@+leo-ver=5-thin
//@+node:swot.20260316162814.1: * @file code/easy-campus/js/main.js
//@@language javascript
//@+others
//@+node:swot.20260316162901.1: ** Ch 1 更新: 初始化 2026-03-16
//@+node:swot.20260314094048.1: *3* BB JSDoc
/**
 * 文件名：main.js
 * 描述：易校园项目核心逻辑入口
 * 作者：[Swot]
 * 日期：2026-03-14 07:53:17
 */

//@+node:swot.20260314094057.1: *3* BB 平台基础配置
// 这些值通常不会变，用 const
const PLATFORM_NAME = "易校园";
const VERSION = "0.0.1";
const AUTHOR = "JavaScript 训练营学员";
//@+node:swot.20260314094109.1: *3* BB 当前登录用户信息（模拟）
// 这些值后续可能会变，用 let
let currentUser = "游客";
let currentMoney = 0;

//@+node:swot.20260314094114.1: *3* BB 控制台输出欢迎信息
console.log(`======================`);
console.log(`欢迎进入【${PLATFORM_NAME}】二手交易平台`);
console.log(`当前版本：v${VERSION}`);
console.log(`开发者：${AUTHOR}`);
console.log(`当前用户：${currentUser}`);
console.log(`钱包余额：${currentMoney} 元`);
console.log(`======================`);

//@+node:swot.20260314094119.1: *3* BB 模拟用户充值
console.log("正在充值 100 元...");
// 这里演示算术运算符
currentMoney = currentMoney + 100; 

//@+node:swot.20260314094123.1: *3* BB 再次输出信息
console.log(`充值成功！当前余额：${currentMoney} 元`);
//@-others
//@-leo
