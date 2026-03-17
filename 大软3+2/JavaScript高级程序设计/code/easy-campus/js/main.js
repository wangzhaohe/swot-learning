//@+leo-ver=5-thin
//@+node:swot.20260316162814.1: * @file code/easy-campus/js/main.js
//@@language javascript
//@+others
//@+node:swot.20260316162901.1: ** Ch 1 更新: 初始化 2026-03-16
console.log('Ch 1 更新: 初始化 2026-03-16');
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
//@+node:swot.20260317110936.1: ** Ch 2 更新: 业务逻辑 2026-03-17
console.log('Ch 2 更新: 业务逻辑 2026-03-17');
//@+node:swot.20260314142703.1: *3* BB 增加用户级别
//@@language javascript
let isVIP = true;      // 是否是VIP会员

let vipLevel = 1;      // VIP等级 1:普通会员 2:超级会员
//@+node:swot.20260314142957.1: *3* BB 商品折扣计算器
//@@language javascript
// 定义商品原价
let price = 100;

// 计算逻辑
let finalPrice = price; // 默认原价

if (vipLevel === 2) {
    finalPrice = price * 0.8; // 8折
    console.log("尊贵的超级会员，您享受8折优惠！");
}
else if (vipLevel === 1) {
    finalPrice = price * 0.9; // 9折
    console.log("尊敬的会员，您享受9折优惠！");
}
else {
    console.log("您暂无折扣，建议开通会员省钱哦~");
}

console.log(`原价：${price}元，折后价：${finalPrice}元`);
//@+node:swot.20260314143438.1: *3* BB 登录身份验证
//@@language javascript
// 利用逻辑运算符 &&
if (isVIP && currentMoney > 0) {
    console.log("验证通过！欢迎进入会员专属区！");
    // 这里后续会跳转页面，现在只打印日志
}
else {
    console.log("您不是会员或余额不足，无法访问该板块！");
}
//@-others
//@-leo
