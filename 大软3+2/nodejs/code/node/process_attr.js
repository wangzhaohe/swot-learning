process.stdout.write("Hello World!" + "\n");  // 输出到终端

process.argv.forEach(function(val, index, array) {  // 通过参数读取
   console.log(index + ': ' + val);
});

console.log(process.execPath);  // 获取执行路径
console.log(process.platform);  // 平台信息
