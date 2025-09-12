//@+leo-ver=5-thin
//@+node:swot.20241018082408.9: * @file src/main/java/com/tjise/service/UserServiceA.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.service;

import com.tjise.dao.UserDaoA;
import com.tjise.pojo.Address;
import com.tjise.pojo.User;

import java.util.List;

public class UserServiceA {
    private UserDaoA userDao = new UserDaoA();

    public List<User> operatorUser() {
        // 调用 Dao 层的方法，获取数据集合
        List<User> list = userDao.operatorUser();

        // 2 业务逻辑操作（把获取的 province 和 city 的值进行处理）
        for (User user : list) {
            Address address = user.getAddress();
            address.setProvince(address.getProvince() + " 省/市");
            address.setCity(address.getCity() + " 市/区");
        }

        return list;
    }
}
//@+doc
// ----
//@-leo
