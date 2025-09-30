//@+leo-ver=5-thin
//@+node:swot.20250907212631.1: * @file src/main/java/com/tjise/interceptor/LoginCheckedInterceptor.java
//@@language java
//@+others
//@+node:swot.20250907212631.2: ** @ignore-node import
//@@language java
package com.tjise.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.tjise.pojo.Result;
import com.tjise.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@+node:swot.20250907212631.3: ** @ignore-node class LoginCheckedInterceptor 前端请求 token 检查拦截器
//@+doc
// [source,java]
// ----
//@@c
@Component
public class LoginCheckedInterceptor implements HandlerInterceptor {  // <1>
    //@+others
    //@+node:swot.20250907222456.1: *3* 注入 redis 对象
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
    //@+node:swot.20250907212631.4: *3* preHandle 目标 api 执行前执行: return true 放行，return false 拦截
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("Enter preHandle()");

        // 1. 获取请求路由
        String url = request.getRequestURL().toString();

        // 2. 判断请求的路由是否包含 login
        if (url.contains("login")) {
            return true;  // 放行去访问 login api
        }

        // 3. 获取请求头中的令牌 token
        String jwt = request.getHeader("token");
        if (jwt == null) {  // 返回登录页面
            sendErrorResponse(response);
            return false;  // 拦截直接给客户端响应 200 结束
        }
        // ---------- 更新代码开始 ------
        // 4. 解析令牌
        Claims payload;
        try {
            payload = JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            sendErrorResponse(response);
            return false;
        }

        // 5. 对比 redis 数据库中的值
              // 如果删除 redis 中用户的键，则能将用户踢出系统
        String username = (String) payload.get("username");
        String tokenFromRedis = stringRedisTemplate.opsForValue()
                                                   .get(username);
        // 如果为 null 说明该用户不在线（如未注册、key过期、被删除踢下线）
        // tokenFromRedis 与 jwt 不相等，说明 jwt 提供的是非法用户
        if (tokenFromRedis == null || !tokenFromRedis.equals(jwt)) {
            sendErrorResponse(response);
            return false;
        }
        // ---------- 更新代码结束 ------
        return true;   // 放行去执行 api
    }
    //@+doc
    // ----
    //
    // .使用 httpie 测试，查看后端日志中打印结果
    // [source,console]
    // ----
    // TOKEN=$(http POST :8080/login username=limuwan password=123456 | jq -r '.data')
    // http :8080/depts "token: $TOKEN"
    // http :8080/depts/1 "token: $TOKEN"
    // ----
    //
    //@+node:swot.20250907212631.5: *3* @ignore-node sendErrorResponse() 自定义发送错误响应方法
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    // 抽取的私有方法
    private void sendErrorResponse(HttpServletResponse response) throws IOException {
        Result notLogin = Result.error("NOT_LOGIN");
        // return notLogin;  // 行不通，因为 preHandle 返回值只能是 boolean
        // 所以可以使用 response.getWriter().write() 给前端返回约定好的 json 格式的数据
        String jsonString = JSONObject.toJSONString(notLogin);
        // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 这个不要设置，会返回401，前端案例没处理
        response.setContentType("application/json;charset=UTF-8");  // 防止中文乱码
        response.getWriter().write(jsonString);

        // 对于不复杂场景，直接返回固定的 JSON 字符串更简单
        // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // response.setContentType("application/json;charset=UTF-8");
        // response.getWriter().write("{\"code\": 401, \"message\": \"NOT_LOGIN\"}")
    }
    //@+doc
    // ----
    //@+node:swot.20250907212631.6: *3* @ignore-node postHandle 目标 api 执行后执行：了解
    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception
    {
        System.out.println("Enter postHandle()");
    }
    //@+node:swot.20250907212631.7: *3* @ignore-node afterCompletion 请求处理后执行：了解
    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, Exception ex) throws Exception
    {
        System.out.println("Enter afterCompletion()");
    }
    //@-others
}
//@+doc
// ----
//
// <1> 实现接口 HandlerInterceptor
//
//@-others
//@-leo
