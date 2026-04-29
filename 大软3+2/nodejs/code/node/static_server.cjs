const http = require("http");  // 加载http模块
const fs = require("fs");      // 加载fs模块
const path = require("path"); // 加载path模块
const url = require("url");   // 加载url模块
const zlib = require("zlib"); // 加载zlib模块
var curDir = ""; //当前目录名
const server = http.createServer(function (req, res) {
    //定义mime对象设置相应的响应头类型，这里仅列出少量的扩展名用于测试
    var mime = {
      ".jpeg": "image/jpeg",
      ".jpg": "image/jpeg",
      ".png": "image/png",
      ".tiff": "image/tiff",
      ".pdf": "application/pdf",
    };
    //获取请求URL并转换请求路径
    var pathName = url.parse(req.url).pathname;

    //对路径进行解码以防中文乱码
    var pathName = decodeURI(pathName);

    //获取资源文件的绝对路径，这里用到全局变量__dirname
    var filePath = path.resolve(__dirname + pathName);
    console.log(filePath); //控制台显示绝对路径

    //获取文件的扩展名
    var extName = path.extname(pathName);

    //为简化处理，没有扩展名的或未知类型使用text/plain表示
    var contentType = mime[extName] || "text/plain";
    //通过读取文件状态来决定如何读取静态文件
    fs.stat(filePath, function (err, stats) {
        if (err) {
            res.writeHead(404, { "content-type": "text/html" });
            res.end("<h1>404 没有找到</h1>");
        }
        //文件存在且没有错误
        if (!err && stats.isFile()) {
          readFile(filePath, contentType);
        }
        //如果路径是目录
        if (!err && stats.isDirectory()) {

            var html = "<head><meta charset = 'utf-8'/></head><body><ul>";
            curDir = path.basename(path.relative(__dirname, filePath)); //获取当前目录名
            //console.log(curDir);

            //读取该路径下的文件
            fs.readdir(filePath, (err, files) => {
                if (err) {
                    console.log("读取路径失败！");
                } else {
                    for (var file of files) {
                        //这里用到了ES6模板字符串
                        var curPath = path.join(curDir, file);
                        html += `<li><a href='${curPath}'>${file}</a></li>`;
                    }
                    html += "</ul></body>";
                    res.writeHead(200, { "content-type": "text/html" });
                    res.end(html);
                }
            });
        }
        //声明函数流式读取文件
        function readFile(filePath, contentType) {
            //设置HTTP消息头
            res.writeHead(200, {
                "content-type": `${contentType}; charset=utf-8`,  // <1>
                "content-encoding": "gzip",
            });
            //创建流对象读取文件
            var stream = fs.createReadStream(filePath);
            //流式读取错误处理
            stream.on("error", function () {
                res.writeHead(500, { "content-type": contentType});
                res.end("<h1>500 服务器错误</h1>");
            });
            //链式管道操作将文件内容流到客户端
            stream.pipe(zlib.createGzip()).pipe(res);
        }

        // <1> charset=utf-8 是为了识别中文字符
        // 参考 https://blog.csdn.net/qq_34817440/article/details/105825141
    });
});
var port = 8000; //指定服务器监听的接口

server.listen(port, function () {
    console.log(`图片服务器正运行在端口:${port}`);
    console.log(`访问网址: http://localhost:${port}`);
});
// 静态文件服务器
