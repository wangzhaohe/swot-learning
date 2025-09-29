//@+leo-ver=5-thin
//@+node:swot.20250825200258.1: * @file src/main/java/com/tjise/aop/AdviceOrderAspectDemo2.java
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
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AdviceOrderAspectDemo2 {  // <1>

    // @Before：此注解标注的通知方法在目标方法前被执行。
    @Before("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")
    public void before() {
        System.out.println("before2");
    }

    // @After ：此注解标注的通知方法在目标方法后被执行，无论是否有异常。
    @After("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")
    public void after() {
        System.out.println("after2");
    }
}
//@@language asciidoc
//@+doc
// ----
//
// <1> 切面类名的字母排序靠后
//@-leo
