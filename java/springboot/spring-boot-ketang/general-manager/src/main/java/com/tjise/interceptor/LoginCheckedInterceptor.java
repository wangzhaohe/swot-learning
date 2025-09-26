//@+leo-ver=5-thin
//@+node:swot.20250819062254.1: * @file src/main/java/com/tjise/interceptor/LoginCheckedInterceptor.java
//@@language java
//@+others
//@+node:swot.20250831211100.1: ** @ignore-node import
package com.tjise.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.tjise.pojo.Result;
import com.tjise.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@+node:swot.20250831211132.1: ** @ignore-node class LoginCheckedInterceptor
//@+doc
// [source,java]
// ----
//@@c
/**
 * 登录检查拦截器
 */
@Component
public class LoginCheckedInterceptor implements HandlerInterceptor {
    //@+others
    //@+node:swot.20250831210446.1: *3* override preHandle -> 增加处理 CORS 预检请求 OPTIONS
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    /**
    * 目标 api 执行前执行: 
    *    return true 放行
    *    return false 拦截
    */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println(
            "[INTERCEPTOR] " + java.time.LocalDateTime.now() + " - Enter preHandle()");

        // 增加处理 CORS 预检请求，这个要加，不加会报错的
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            System.out.println(
                "[INTERCEPTOR] " + java.time.LocalDateTime.now() + " - Handling OPTIONS request");
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        // 其他拦截逻辑
        //@+others
        //@+node:swot.20250831211507.1: *4* @ignore-node 其他拦截逻辑
        // 1. 获取请求路由
        String url = request.getRequestURL().toString();

        // 2. 判断请求的路由是否包含 login
        if (url.contains("login")) {
            // 放行去访问 login api
            return true;
        }

        // 3. 获取请求头中的令牌 token
        String jwt = request.getHeader("token");
        if (jwt == null) {  // 返回登录页面
            sendErrorResponse(response);
            return false;
        }

        // 4. 解析令牌
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            sendErrorResponse(response);
            return false;
        }   

        return true;   // 放行去执行 api
        // return false;  // 拦截直给客户端响应 200 结束
        //@-others
    }
    //@+doc
    // ----
    //@+node:swot.20250831210552.1: *3* @ignore-tree
    //@+others
    //@+node:swot.20250831210440.1: *4* override postHandle
    // 目标 api 执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Enter postHandle()");
    }
    //@+node:swot.20250831210435.1: *4* override afterCompletion
    // 请求处理后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Enter afterCompletion()");
    }
    //@+node:swot.20250819084555.1: *4* private void sendErrorResponse
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    // 抽取的私有方法
    private void sendErrorResponse(HttpServletResponse response) throws IOException {
        Result notLogin = Result.error("NOT_LOGIN");
        // return notLogin;  // 行不通，因为 doFilter 返回值只能是 void
        // 所以可以使用 response.getWriter().write() 给前端返回约定好的 json 格式的数据
        String jsonString = JSONObject.toJSONString(notLogin);
        // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 这个不要设置，会返回401，前端案例没处理
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonString);

        // 对于不复杂场景，直接返回固定的 JSON 字符串更简单
        // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // response.setContentType("application/json;charset=UTF-8");
        // response.getWriter().write("{\"code\": 401, \"message\": \"NOT_LOGIN\"}")
    }
    //@+doc
    // ----
    //
    //@-others
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
