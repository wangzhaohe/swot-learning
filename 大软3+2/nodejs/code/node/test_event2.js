import { EventEmitter } from 'events';  // <1>

var event = new EventEmitter();   // <2>

event.on('seen', function(who) {  // <3>
    console.log('报告，来人是一位', who);
});
event.on('seen', function() {  // <4>
    console.log('欢迎光临！');
});
event.emit('seen', '女士');  // <5>
