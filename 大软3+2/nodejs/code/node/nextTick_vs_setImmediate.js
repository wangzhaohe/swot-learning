import { readFile } from 'fs';
import { fileURLToPath } from 'url';
import { dirname } from 'path';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

// EventLoop: timers
// EventLoop: pending callbacks
// EventLoop: idle, prepare
// EventLoop: poll
readFile(__filename, () => {  // <1>

    setTimeout(() => {  // <2>
        console.log('setTimeout');  // <7>
    }, 0);

    setImmediate(() => {  // to check <3>
        console.log('setImmediate');  // <5>
        process.nextTick(() => {
          console.log('nextTick 2');  // <6>
        });
    });

    process.nextTick(() => {  // <4>
        console.log('nextTick 1');
    });
// EventLoop: check
// EventLoop: close callbacks
});
