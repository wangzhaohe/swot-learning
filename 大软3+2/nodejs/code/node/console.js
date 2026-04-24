console.log('hello world');          // 打印hello world到标准输出流
console.log('hello %s', 'world');    // 打印hello world到标准输出流

console.error(new Error('错误信息')); // 打印 [Error: 错误信息] 到标准错误流

const name = 'Robert';
console.warn(`Danger ${name}! Danger!`);  // 打印 Danger Robert! Danger!到标准错误流
