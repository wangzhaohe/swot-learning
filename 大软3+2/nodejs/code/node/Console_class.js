import fs from 'fs';
import { Console } from 'console';

// 创建写入流
const output = fs.createWriteStream('./stdout.log');
const errorOutput = fs.createWriteStream('./stderr.log');

// 实例化 logger
const logger = new Console(output, errorOutput);  // <.>

// 像 console 一样使用 logger
const count = 5;
logger.log('count: %d', count);  // <.> 

logger.error(new Error("错误信息"));  // <.>

const name = "Swot";
logger.warn(`Danger ${name}! Danger!`);  // <.>
