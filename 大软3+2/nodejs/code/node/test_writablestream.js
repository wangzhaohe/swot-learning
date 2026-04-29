import fs from "node:fs";

const str = '这是测试可写流的数据，保存起来';
const writerStream = fs.createWriteStream('node/output.txt');  // <1>

writerStream.write(str, 'UTF8');  // <2>

writerStream.end();  // <3>

// 处理流事件
writerStream.on('finish', function() {
    console.log('写入完成!');
});

writerStream.on('error', function(err) {
    console.log('写入失败');
});
