//@+leo-ver=5-thin
//@+node:swot.20250816215207.1: * @file src/main/java/com/tjise/controller/LoginController.java
//@@language java
//@+doc
// [source,java]
// ----
//@@c
//@+others
//@+node:swot.20250907113010.1: ** @ignore-node import
package com.tjise.controller;

import com.tjise.pojo.Emp;
import com.tjise.pojo.Result;
import com.tjise.service.EmpService;
import com.tjise.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
//@-others
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        // 登录，去数据库验证用户名和密码
        Emp e = empService.login(emp);

        if (e == null) {
            // 登录失败返回 NOT_LOGIN 后前端会自动跳转到登录页面
            return Result.error("NOT_LOGIN");
        } else {
            // 登录成功
            Map<String, Object> map = new HashMap<>();
            map.put("id", e.getId());
            map.put("username", e.getUsername());
            map.put("name", e.getName());

            // 生成令牌并返回给前端
            String jwt = JwtUtils.generateJwt(map);
            return Result.success(jwt);
        }
    }
}
//@+doc
// ----
//
// 使用 httpie 测试登录::    
// http -v post http://localhost:8080/login username=limuwan password=123456
//@-leo
