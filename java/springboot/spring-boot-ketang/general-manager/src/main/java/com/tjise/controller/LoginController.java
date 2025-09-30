//@+leo-ver=5-thin
//@+node:swot.20250907160742.1: * @file src/main/java/com/tjise/controller/LoginController.java
//@@language java
//@+doc
// [source,java]
// ----
//@@c
//@+others
//@+node:swot.20250907160742.2: ** @ignore-node import
package com.tjise.controller;

import com.tjise.pojo.Emp;
import com.tjise.pojo.Result;
import com.tjise.service.EmpService;
import com.tjise.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//@-others
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    //@+<< 注入 redis 对象 >>
    //@+node:swot.20250907161802.1: ** << 注入 redis 对象 >>
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //@+doc
    // ----
    //
    //@-<< 注入 redis 对象 >>

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

            //@+<< 将 jwt token 存入 redis 数据库 >>
            //@+node:swot.20250907162203.1: ** << 将 jwt token 存入 redis 数据库 >>
            //@+doc
            // [source,java]
            // ----
            //@@c
            //@@language java
            stringRedisTemplate.opsForValue().set(
                    e.getUsername(),       // key，将用户名 username 存入 redis
                    jwt,                   // value，将 token 存入 redis
                    JwtUtils.expire,       // 过期时间
                    TimeUnit.MILLISECONDS  // 时间单位
            );
            //@+doc
            // ----
            //@-<< 将 jwt token 存入 redis 数据库 >>

            return Result.success(jwt);
        }
    }
}
//@+doc
// ----
//@-leo
