/**
 * @fileoverview Hello World 服务器示例 (Express)
 * @description 使用 Express 框架创建简单的 HTTP 服务器
 * @author swot
 * @version 1.0.0
 */

import express from 'express';
const app = express();

app.get('/', (req, res) => {
    res.send("Hello World!");  // Express 自动处理 Content-Type 和状态码
});

/**
 * 启动服务器并监听指定端口
 * @param {number} port - 监听端口号
 * @param {function} [callback] - 服务器启动后的回调函数
 */
app.listen(8081, () => {
    console.log('服务器正在 8081 端口上监听');
});
