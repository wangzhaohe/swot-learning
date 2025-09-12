//@+leo-ver=5-thin
//@+node:swot.20241017145139.1: * @file spring-boot-ketang/spring-boot-request-response/src/main/java/com/tjise/controller/UserController.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.controller;

import com.tjise.pojo.Address;
import com.tjise.pojo.Result;
import com.tjise.pojo.User;
import com.tjise.utils.XmlParserUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @RequestMapping("/listUser")
    public Result listUser() {
        // 1 获取数据（解析 xml 文件，把数据封装到对象中）
        // 动态获取 user.xml 文件绝对路径
        String path = UserController.class.getClassLoader()
                      .getResource("user.xml").getPath();
        System.out.println("path = " + path);
        List<User> list = XmlParserUtils.parse(path);

        // 2 业务逻辑操作（把获取的 province 和 city 的值进行处理）
        for (User user : list) {
            Address address = user.getAddress();
            address.setProvince(address.getProvince() + " 省/市");
            address.setCity(address.getCity() + " 市/区");
        }

        // 3 把封装的数据响应给客户端
        return Result.success(list);
    }
}
//@+doc
// ----
//@-leo
