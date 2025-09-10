//@+leo-ver=5-thin
//@+node:swot.20241011155227.12: * @file spring-boot-ketang/hello-spring-boot/src/main/java/com/tjise/HelloController.java
//@@language java
//@+doc
// .服务端路由 /hello，返回字符串 "Hello World!"
// [source,java,linenums]
// ----
//@@c
package com.tjise;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("Hello World");
        return "Hello World";
    }
}
//@+doc
// ----
//@-leo
