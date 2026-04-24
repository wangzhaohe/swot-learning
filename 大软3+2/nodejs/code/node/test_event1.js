import { EventEmitter as _EventEmitter } from 'events';  // <1>

const myEmitter = new _EventEmitter();  // <2>

myEmitter.on('seen', () => {  // <3><5>
    console.log('报告，有人来了');
});

myEmitter.emit('seen');  // <4>
