import fs from 'fs';
import { fileURLToPath } from 'url';
import { dirname } from 'path';

// 获取当前文件的路径（ESM 中需要手动获取 __filename 和 __dirname）
const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

// EventLoop: timers
// EventLoop: pending callbacks
// EventLoop: idle, prepare
// EventLoop: poll
fs.readFile(__filename, () => {  // <1>

    setTimeout(() => {  // <2>
        console.log('setTimeout');
    }, 0);

    setImmediate(() => {  // <3>
        console.log('setImmediate');
    });
});
// EventLoop: check
// EventLoop: close callbacks
