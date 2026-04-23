import { readFile } from "fs";  // <1>

readFile('node/test_callback.txt', function (err, data) {  // <2>
    if (err) return console.error(err);  // <3>
    console.log(data.toString());   // <4>
});

console.log("Node 程序已经执行结束!");  // <5>
