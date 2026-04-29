console.log("程序执行开始");  // 1. 立即执行

process.on('exit', function(code) {
  // 3. exit 事件触发，事件循环已停止
  setTimeout(function(){
      // 5. 永远不会执行（exit 事件中的 setTimeout 回调不会执行，因为事件循环已经停止）
      console.log("该代码不会执行");
  }, 0);
  // 4. 这个会执行（同步代码）
  console.log('退出码为:', code);
});

console.log("程序执行结束");  // 2. 立即执行
