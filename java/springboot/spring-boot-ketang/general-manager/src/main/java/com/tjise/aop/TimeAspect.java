//@+leo-ver=5-thin
//@+node:swot.20250825153650.1: * @file src/main/java/com/tjise/aop/TimeAspect.java
//@+doc
// [source,java,linenum]
// ----
//@@c
//@@language java
//@+others
//@+node:swot.20250926160204.1: ** @ignore-node import
//@@language java
package com.tjise.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@-others
@Slf4j
@Aspect     // <2>
@Component  // <2>
public class TimeAspect {  // 切面类
    @Around("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")  // <3>
    public Object getTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();     // <1>

        Object obj = joinPoint.proceed();            // <4>

        long end = System.currentTimeMillis();       // <1>
        log.info("方法执行耗时: {} ms", (end-begin));  // <1>

        return obj;                                  // <4>
    }
}
//@+doc
// ----
//
// <1> 抽取公共代码（统计执行耗时操作）。这些公共代码称为通知(Advice)。
// <2> 标识当前类是一个 AOP 类，并被 IOC 容器管理。
// <3> 配置公共代码作用于哪些目标方法上。通过 execution 中的切入点表达式计算得到切入点，从而找到目标方法。
// +
// - Around 的名称叫**通知**。
// - 第一个星号 * 表示匹配所有方法的返回值类型，即任何方法的返回值类型都能被匹配。
// - 第二个星号 * 表示匹配所有方法。
// - .. 表示匹配任意多个参数。
// <4> 执行目标方法并返回值。joinPoint 代表目标方法。
//
// .execution 基本语法
// ****
// execution(返回值类型 包名.类名.方法名(参数类型))
//
// - *：可以通配任意一个 返回值类型、包名、类名、方法名、或任意的一个参数类型。
// - ..：可以通配任意层级的包、或任意类型、任意个数的参数。
// ****
//
// .使用 httpie 测试，查看后端日志中已经打印了方法执行耗时
// [source,console]
// ----
// TOKEN=$(http POST :8080/login username=limuwan password=123456 | jq -r '.data')
// http :8080/depts "token: $TOKEN"
// http :8080/depts/1 "token: $TOKEN"
// ----
//
// // 此处可以补充打印日志
//@-leo
