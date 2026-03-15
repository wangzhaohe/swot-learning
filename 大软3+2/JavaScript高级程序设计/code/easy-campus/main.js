//@+leo-ver=5-thin
//@+node:swot.20260313172511.1: * @file code/easy-campus/main.js
//@@language javascript
//@+others
//@+node:swot.20260314145416.1: ** Ch 1 更新: 初始化 2026-03-12
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
const VERSION = "1.0.0";
const AUTHOR = "JavaScript训练营学员";

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
//@+node:swot.20260314144707.1: ** Ch 2 更新: 业务逻辑 2026-03-13
//@+node:swot.20260314142703.1: *3* BB 增加用户级别
//@@language javascript
let isVIP = true;      // 是否是VIP会员
let vipLevel = 1;      // VIP等级 1:普通会员 2:超级会员
//@+node:swot.20260314143438.1: *3* BB 登录身份验证
//@@language javascript
// 利用逻辑运算符 &&
if (isVIP && currentMoney > 0) {
    console.log("验证通过！欢迎进入会员专属二手区！");
    // 这里后续会跳转页面，现在只打印日志
}
else {
    console.log("您不是会员或余额不足，无法访问该板块！");
}
//@+node:swot.20260314145454.1: ** Ch 3 更新: 数据模拟 2026-03-14
//@+node:swot.20260314150831.1: *3* BB 定义商品仓库
//@@language javascript
let goodsList = [];  // 定义商品仓库
//@+node:swot.20260314151757.1: *3* BB 调用函数存入端口仓库
//@@language javascript
// 调用函数，生成20个商品
generateGoods(20);

// 在控制台打印查看
console.log(goodsList);
//@+node:swot.20260314151902.1: *3* BB 循环打印商品清单预览
//@@language javascript
console.log("========== 商品清单预览 ==========");

for (let i = 0; i < goodsList.length; i++) {
    // 取出第 i 个商品
    let item = goodsList[i];
    // 打印简略信息
    console.log(`ID: ${item.id} | 名称: ${item.name} | 价格: ${item.price}元`);
}
//@+node:swot.20260314165739.1: ** Ch 4 更新: 重构升级 2026-03-14
//@+node:swot.20260314165448.1: *3* 重构“批量数据生成器” generateGoods()
//@@language javascript
/**
 * 生成商品数据（升级版）
 * @param {number} count
 */
function generateGoods(count) {
    goodsList = []; // 清空原数组
    const names = ["高等数学", "大学英语", "计算机网络", "二手自行车", "台灯"];

    for (let i = 1; i <= count; i++) {
        // 1. 生成随机原价
        let originalPrice = Math.floor(Math.random() * 100) + 10;

        // 2. 随机会员等级 (0, 1, 2)
        let randomLevel = Math.floor(Math.random() * 3);

        // 3. 调用计算折扣函数
        let finalPrice = calculatePrice(originalPrice, randomLevel);

        let goods = {
            id: i,
            name: names[Math.floor(Math.random() * names.length)] + "-" + i,
            originalPrice: originalPrice,
            finalPrice: finalPrice,
            createTime: getFormatDate() // 调用时间函数
        };

        goodsList.push(goods);
    }
    console.log("商品数据生成完毕：", goodsList);
}

//@+node:swot.20260314165234.1: *4* 重构“商品折扣计算” calculatePrice()
//@@language javascript
/**
 * 功能：计算折扣价格
 * @param {number} originalPrice - 原价
 * @param {number} level - 会员等级 (1 or 2)
 * @returns {number} - 最终价格
 */
function calculatePrice (originalPrice, level) {
    if (level === 2) {
        return originalPrice * 0.8;
    }
    else if (level === 1) {
        return originalPrice * 0.9;
    }
    else {
        return originalPrice;
    }
};

//@+node:swot.20260314165417.1: *4* 重构“日期格式化” getFormatDate()
//@@language javascript
/**
 * 功能：获取当前格式化时间
 * @returns {string} - 如 "2023-10-25 14:30:00"
 */
function getFormatDate() {

    const date = new Date();

    let y = date.getFullYear();
    let m = date.getMonth() + 1; // 月份要+1
    let d = date.getDate();
    let h = date.getHours();
    let min = date.getMinutes();
    let s = date.getSeconds();

    // 补零操作：如果是个位数，前面加0。使用三元运算符
    m = m < 10 ? "0" + m : m;
    d = d < 10 ? "0" + d : d;
    h = h < 10 ? "0" + h : h;
    min = min < 10 ? "0" + min : min;
    s = s < 10 ? "0" + s : s;

    return `${y}-${m}-${d} ${h}:${min}:${s}`;
}

//@+node:swot.20260314234531.1: ** Ch 5 更新: 数据仓库 2026-03-14
//@+node:swot.20260314232813.1: *3* BB 专门管理商品的对象 GoodsDB = {}
//@@language javascript
const GoodsDB = {
    //@+others
    //@+node:swot.20260314233406.1: *4* 1. 添加商品 add
    //@@language javascript
    add: function(name, price, category) {
        // 生成ID：取数组最后一个元素的ID + 1，如果数组为空则从1开始
        let id = goodsList.length > 0 ? goodsList[goodsList.length - 1].id + 1 : 1;
        let newGoods = {
            id: id,
            name: name,
            price: price,
            category: category,
        };
        goodsList.push(newGoods);
        console.log(`商品【${name}】添加成功！`);
    },
    //@+node:swot.20260314233347.1: *4* 2. 删除商品 deleteById
    //@@language javascript
    deleteById: function(id) {
        // 遍历查找索引
        let index = -1;

        for (let i = 0; i < goodsList.length; i++) {
            if (goodsList[i].id === id) {
                index = i;
                break;
            }
        }
        // 也可以用 findIndex 方法（明天的内容）

        if (index !== -1) {
            let delName = goodsList[index].name;
            goodsList.splice(index, 1);
            console.log(`商品【${delName}】已删除！`);
        }
        else {
            console.log("未找到该ID的商品！");
        }
    },
    //@+node:swot.20260314233318.1: *4* 3. 打印列表 show
    //@@language javascript
    show: function() {
        // console.table 可以用表格形式展示数组对象，非常直观
        console.table(goodsList);
    }
    //@-others
};
//@+node:swot.20260314234349.1: *3* 测试 GoodsDB 操作数据
GoodsDB.show();
GoodsDB.add("大学英语", 10, "书籍");
GoodsDB.deleteById(1);
GoodsDB.show();
//@+node:swot.20260315104045.1: ** Ch 6 更新: 筛选与时间 2026-03-15
//@+node:swot.20260315085646.1: *3* BB 筛选商品 filterByCategory(category)
//@@language javascript
// 筛选特定类别的商品
function filterByCategory(category) {
    if (category === "全部") {
        return goodsList;
    }
    // 使用 filter
    return goodsList.filter(item => item.category === category);
}

//@+node:swot.20260315104339.1: *3* 测试筛选商品
//@@language javascript
let books = filterByCategory("书籍");
console.log("所有书籍类商品：", books);
//@+node:swot.20260315101941.1: *3* BB 格式化商品发布时间 formatTime()
//@@language javascript
function formatTime(dateStr) {
    // 假设传入的发布时间是时间戳或日期字符串，这里模拟一个时间戳
    let pubTime = new Date(dateStr).getTime(); // 发布时间的毫秒数
    let now = Date.now(); // 当前时间的毫秒数
    let diff = (now - pubTime) / 1000; // 转换成秒差值

    if (diff < 60) {
        return "刚刚";
    } else if (diff < 3600) {
        return Math.floor(diff / 60) + "分钟前";
    } else if (diff < 3600 * 24) {
        return Math.floor(diff / 3600) + "小时前";
    } else {
        // 超过1天，显示具体日期
        let d = new Date(dateStr);
        return `${d.getFullYear()}-${d.getMonth()+1}-${d.getDate()}`;
    }
}
//@-others
//@-leo
