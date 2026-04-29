console.log('outer');  // 先执行

// EventLoop: timers
setTimeout(() => {

    setTimeout(() => {
        console.log('setTimeout');
    }, 0);

    setImmediate(() => {
        console.log('setImmediate');
    });

}, 0);

