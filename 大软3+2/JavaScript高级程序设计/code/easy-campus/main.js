/**
 * 文件名：main.js
 * 描述：易校园项目核心逻辑入口
 * 作者：[你的名字]
 * 日期：2023-XX-XX
 */

// 1. 平台基础配置
// 这些值通常不会变，用 const
const PLATFORM_NAME = "易校园";
const VERSION = "1.0.0";
const AUTHOR = "JavaScript训练营学员";

// 2. 当前登录用户信息（模拟）
// 这些值后续可能会变，用 let
let currentUser = "游客";
let currentMoney = 0;

// 3. 控制台输出欢迎信息
console.log(`======================`);
console.log(`欢迎进入【${PLATFORM_NAME}】二手交易平台`);
console.log(`当前版本：v${VERSION}`);
console.log(`开发者：${AUTHOR}`);
console.log(`当前用户：${currentUser}`);
console.log(`钱包余额：${currentMoney} 元`);
console.log(`======================`);

// 4. 模拟用户充值
console.log("正在充值 100 元...");
// 这里演示算术运算符
currentMoney = currentMoney + 100; 

// 5. 再次输出信息
console.log(`充值成功！当前余额：${currentMoney} 元`);
