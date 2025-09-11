//@+leo-ver=5-thin
//@+node:swot.20241016134143.17: * @file src/main/java/com/tjise/controller/ResponseController.java
//@@language java
//@+doc
// .使用 Result 改造后的代码
// [source,java,linenums]
// ----
//@@c
package com.tjise.controller;

import com.tjise.pojo.Result;
import com.tjise.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// 已经包含 @ResponseBody 注解
@RestController
public class ResponseController {
    // 此处用 @others 表示还有很多代码
    //@+others
    //@+node:swot.20241016134143.18: ** return Result -> String
    //@+doc
    // .使用 Result 封装响应数据
    // [source,java,linenums,highlight=5]
    // ----
    //@@language java
    //@@c
    @RequestMapping("/hello")
    public Result hello(){
        System.out.println("Hello World!");
        // Result result = new Result(1, "success", "Hello World!");
        return Result.success("Hello World!");  // 调用静态方法 success
    }
    //@+doc
    // ----
    //
    // .访问 http://localhost:8080/hello
    // ....
    // {
    //     "code": 1,
    //     "msg": "success",
    //     "data": "Hello World!"
    // }
    // ....
    //@+node:swot.20241016134143.19: ** return Result -> User
    //@+doc
    // .使用 Result 封装响应数据
    // [source,java,linenums,highlight=11]
    // ----
    //@@language java
    //@@c
    @RequestMapping("/getUser")
    public Result getUser(){

        // User user = new User("王林", 400, null);
        User user = new User();
        user.setName("王林");
        user.setAge(400);

        System.out.println(user);
        // return new Result(1, "success", user);
        return Result.success(user);  // 调用静态方法 success
    }
    //@+doc
    // ----
    //
    // .访问 http://localhost:8080/getUser
    // ....
    // {
    //     "code": 1,
    //     "msg": "success",
    //     "data": {
    //         "name": "王林",
    //         "age": 400,
    //         "address": null
    //     }
    // }
    // ....
    //@+node:swot.20241016134143.20: ** return Result -> List
    //@+doc
    // .使用 Result 封装响应数据
    // [source,java,linenums,highlight=12]
    // ----
    //@@language java
    //@@c
    @RequestMapping("/getList")
    public Result getList(){
        User user = new User();
        user.setName("王林");
        user.setAge(400);

        List<User> list = new ArrayList<>();
        list.add(user);

        System.out.println(list);
        // return new Result(1, "susscess", list);
        return Result.success(list);  // 调用静态方法 success
    }
    //@+doc
    // ----
    //
    // .访问 http://localhost:8080/getList
    // ....
    // {
    //     "code": 1,
    //     "msg": "susscess",
    //     "data": [
    //         {
    //             "name": "王林",
    //             "age": 400,
    //             "address": null
    //         }
    //     ]
    // }
    // ....
    //@-others
}
//@+doc
// ----
//@-leo
