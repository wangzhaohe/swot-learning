//@+leo-ver=5-thin
//@+node:swot.20241018082408.11: * @file src/main/java/com/tjise/controller/UserController.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.controller;

import com.tjise.pojo.Address;
import com.tjise.pojo.Result;
import com.tjise.pojo.User;
import com.tjise.service.UserServiceA;
import com.tjise.utils.XmlParserUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserServiceA userService = new UserServiceA();

    @RequestMapping("/listUser")
    public Result listUser() {
        List<User> list = userService.operatorUser();
        // 3 把封装的数据响应给客户端
        return Result.success(list);
    }
}
//@+doc
// ----
//@-leo
