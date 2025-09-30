//@+leo-ver=5-thin
//@+node:swot.20250826122200.1: * @file src/main/java/com/tjise/aop/JoinPointAspect.java
//@+doc
// [source,java,linenum]
// ----
//@@c
//@@language java
package com.tjise.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class JoinPointAspect {
    @Around("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")
    public Object getTime(ProceedingJoinPoint joinPoint) throws Throwable {  // <1>

        String className = joinPoint.getTarget().getClass().getName();  // 获取类名
        String methodName = joinPoint.getSignature().getName();   // 获取方法名
        String args = Arrays.toString(joinPoint.getArgs());  // 获取方法参数值
        log.info("类名：{}，方法名：{}，参数：{}", className, methodName, args);  // <2>

        Object obj = joinPoint.proceed();  // <1>
        return obj;
    }
}
//@@language asciidoc
//@+doc
// ----
//
// <1> joinPoint 连接点代表方法对象。
// <2> 在后端打印获取到的内容。
//
// .使用 httpie 测试，查看后端日志中打印结果
// [source,console]
// ----
// TOKEN=$(http POST :8080/login username=limuwan password=123456 | jq -r '.data')
// http :8080/depts "token: $TOKEN"
// http :8080/depts/1 "token: $TOKEN"
// ----
//@-leo
