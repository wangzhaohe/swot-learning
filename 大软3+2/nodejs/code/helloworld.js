/**
 * @fileoverview Hello World 服务器示例
 * @description 使用 Node.js 原生 http 模块创建简单的 HTTP 服务器
 * @author swot
 * @version 1.0.0
 */

import http from 'http';  // <1>

const httpServer = http.createServer(function (req, res) {  // <2>
    res.writeHead(200, {'Content-Type': 'text/plain'});  // <3>
    res.end("Hello World!");  // <4>
});

/**
 * 启动服务器并监听指定端口
 * @param {number} port - 监听端口号
 * @param {function} [callback] - 服务器启动后的回调函数
 */
httpServer.listen(8081, function(){  // <5>
    console.log(
        '服务器正在 8081 端口上监听!\n',
        'http://127.0.0.1:8081'
    );  // <6>
});
