//@+leo-ver=5-thin
//@+node:swot.20250825204751.1: * @file src/main/java/com/tjise/aop/AdviceOrderAspectDemo3.java
//@+doc
// [source,java,linenum]
// ----
//@@c
//@@language java
package com.tjise.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
@Order(99)  // <1>
public class AdviceOrderAspectDemo3 {  // 切面类

    // @Before：此注解标注的通知方法在目标方法前被执行。
    @Before("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")
    public void before() {
        System.out.println("before3");
    }

    // @After：此注解标注的通知方法在目标方法后被执行，无论是否有异常。
    @After("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")
    public void after() {
        System.out.println("after3");
    }
}
//@@language asciidoc
//@+doc
// ----
//
// <1> 用大数字注解类名
//@-leo
