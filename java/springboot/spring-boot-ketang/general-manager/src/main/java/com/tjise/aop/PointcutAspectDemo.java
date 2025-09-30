//@+leo-ver=5-thin
//@+node:swot.20250826104547.1: * @file src/main/java/com/tjise/aop/PointcutAspectDemo.java
//@+doc
// [source,java,linenum]
// ----
//@@c
//@@language java
package com.tjise.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class PointcutAspectDemo {

    @Pointcut("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")  // <1>
    public void pt(){} // 自定义名称，可以理解为 pointcut 简写

    // @Before：此注解标注的通知方法在目标方法前被执行。
    @Before("pt()")  // <2>
    public void before() {
        System.out.println("before by @Pointcut");
    }

    // @After ：此注解标注的通知方法在目标方法后被执行，无论是否有异常。
    @After("pt()")  // <2>
    public void after() {
        System.out.println("after by @Pointcut");
    }
}
//@@language asciidoc
//@+doc
// ----
//
// <1> 使用 @Pointcut 注解抽取公共的切点表达式为一个方法，命名为 pt()。
// <2> 引用抽取的方法即可。
//
// .使用 httpie 测试，查看后端日志中已经打印了方法执行耗时
// [source,console]
// ----
// TOKEN=$(http POST :8080/login username=limuwan password=123456 | jq -r '.data')
// http :8080/depts "token: $TOKEN"    # 会打印 before  by @Pointcut 和 after by @Pointcut
// http :8080/depts/1 "token: $TOKEN"  # 会打印 before  by @Pointcut 和 after by @Pointcut
// ----
//@-leo
