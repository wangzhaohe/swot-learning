import fs from 'node:fs';

const readStream = fs.createReadStream('node/demo.txt');  // <1>

let str = '';

readStream.on('data', (chunk) => {  // <2>
    str += chunk;
});

readStream.on('end', () => {  // <3>
    console.log(str);
});

readStream.on('error', (err) => {  // <4>
    console.log(err);
});
