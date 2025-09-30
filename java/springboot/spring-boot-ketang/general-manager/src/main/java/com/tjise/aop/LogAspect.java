//@+leo-ver=5-thin
//@+node:swot.20250826233540.1: * @file src/main/java/com/tjise/aop/LogAspect.java
//@@language java
//@+others
//@+node:swot.20250930093415.1: ** @ignore-node import
package com.tjise.aop;

import com.alibaba.fastjson2.JSONObject;
import com.tjise.mapper.OperateLogMapper;
import com.tjise.utils.JwtUtils;
import com.tjise.pojo.OperateLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
//@+node:swot.20250930093548.1: ** class LogAspect
//@+doc
// [source,java]
// ----
//@@c
@Aspect
@Component
public class LogAspect {

    // request 含有用户上传的参数，其中有 token 可以解出登录的用户信息，如 id
    @Autowired
    private HttpServletRequest request;

    // 把 OperateLogMapper 接口的实现类对象注入
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.tjise.annotation.Log)")
    public Object logServiceCUD(ProceedingJoinPoint joinPoint) throws Throwable {
        // CUD 表示 CRUD 排除了查询的 READ 业务
        //@+others
        //@+node:swot.20250826235039.1: *3* 1 获取操作人 operateUser -> 通过 token 获取登录用户 id
        //@+doc
        // .解析出已登录用户的 id
        // [source,java]
        // ----
        //@@c
        //@@language java
        String jwt = request.getHeader("token");
        Integer operateUser = (Integer) JwtUtils.parseJWT(jwt).get("id");
        //@+doc
        // ----
        //@+node:swot.20250827001323.1: *3* 2 获取操作时间 operateTime -> 取服务器当前操作时间
        //@+doc
        // [source,java]
        // ----
        //@@c
        //@@language java
        LocalDateTime operateTime = LocalDateTime.now();
        //@+doc
        // ----
        //
        //@+node:swot.20250827083742.1: *3* 3 获取操作方法的类名 className     -> joinPoint
        //@+doc
        // [source,java]
        // ----
        //@@c
        //@@language java
        String className = joinPoint.getTarget().getClass().getName();
        //@+doc
        // ----
        //
        //@+node:swot.20250827085745.1: *3* 4 获取操作的方法名 methodName      -> joinPoint
        //@+doc
        // [source,java]
        // ----
        //@@c
        //@@language java
        String methodName = joinPoint.getSignature().getName();
        //@+doc
        // ----
        //
        //@+node:swot.20250827091752.1: *3* 5 获取操作的方法参数 methodParams  -> joinPoint
        //@+doc
        // [source,java]
        // ----
        //@@c
        //@@language java
        String methodParams = Arrays.toString(joinPoint.getArgs());
        //@+doc
        // ----
        //
        //@+node:swot.20250827091958.1: *3* 6 获取操作方法的返回值 returnValue -> joinPoint
        //@+doc
        // [source,java]
        // ----
        //@@c
        //@@language java
        long begin = System.currentTimeMillis();  // 操作方法执行开始时间
        Object result = joinPoint.proceed();      // 执行方法，有异常就抛出
        long end = System.currentTimeMillis();    // 操作方法执行结束时间
        // 对象转字符串
        String returnValue = JSONObject.toJSONString(result);
        //@+doc
        // ----
        //
        //@+node:swot.20250827105531.1: *3* 7 获取操作方法执行耗时 costTime    -> end - begin
        //@+doc
        // [source,java]
        // ----
        //@@c
        //@@language java
        long costTime = end - begin;
        //@+doc
        // ----
        //
        //@+node:swot.20250827111836.1: *3* 8 获取的数据封装成 OperateLog 对象 -> new OperateLog
        //@+doc
        // [source,java]
        // ----
        //@@c
        //@@language java
        OperateLog operateLog = new OperateLog(
                null,  // id 由数据库自动生成
                operateUser,
                operateTime,
                className,
                methodName,
                methodParams,
                returnValue,
                costTime
        );
        //@+doc
        // ----
        //
        //@+node:swot.20250827113326.1: *3* 9 将封装的 OperateLog 对象插入数据库表 operate_log 中 -> mapper
        //@+doc
        // [source,java]
        // ----
        //@@c
        //@@language java
        operateLogMapper.insert(operateLog);
        //@+doc
        // ----
        //
        //@+node:swot.20250827111333.1: *3* 10 返回操作方法执行的结果 result
        //@+doc
        // [source,java]
        // ----
        //@@c
        //@@language java
        // @Around 注解的方法必须返回对象
        return result;
        //@+doc
        // ----
        //
        //@-others
    } 
}
//@+doc
// ----
//@-others
//@-leo
