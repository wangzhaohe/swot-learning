global.x = 5;

// setTimeout 是异步函数，后打印
//此处用到的回调函数的形式是箭头函数，() => 相当于 function()
setTimeout(() => {
  console.log('world');
}, 1000);

// 同步代码先打印
console.log('hello');

/* 打印结果
hello
world
*/
