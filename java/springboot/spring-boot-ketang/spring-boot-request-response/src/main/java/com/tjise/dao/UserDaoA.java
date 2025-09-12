//@+leo-ver=5-thin
//@+node:swot.20241018082408.7: * @file src/main/java/com/tjise/dao/UserDaoA.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.dao;

import com.tjise.controller.UserController;
import com.tjise.pojo.User;
import com.tjise.utils.XmlParserUtils;

import java.util.List;

public class UserDaoA {
    public List<User> operatorUser() {
        // 1 操作数据（解析 xml 文件，把数据封装到对象中）
        // 动态获取 user.xml 文件绝对路径
        String path = UserController.class.getClassLoader()
                .getResource("user.xml").getPath();
        System.out.println("path = " + path);
        List<User> list = XmlParserUtils.parse(path);
        return list;
    }
}
//@+doc
// ----
//@-leo
