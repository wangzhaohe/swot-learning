import http from 'http';  // <1>

const httpServer = http.createServer(function (req, res) {  // <2>
    res.writeHead(200, {'Content-Type': 'text/plain'});  // <3>
    res.end("Hello World!");  // <4>
});

httpServer.listen(8081, function(){  // <5>
    console.log(
        '服务器正在 8081 端口上监听!\n',
        'http://127.0.0.1:8081'
    );  // <6>
});
