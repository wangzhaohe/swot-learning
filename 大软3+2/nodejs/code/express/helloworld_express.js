import express from 'express';
const app = express();

app.get('/', (req, res) => {
    res.send("Hello World!");  // Express 自动处理 Content-Type 和状态码
});

app.listen(8081, () => {
    console.log('服务器正在 8081 端口上监听');
});
