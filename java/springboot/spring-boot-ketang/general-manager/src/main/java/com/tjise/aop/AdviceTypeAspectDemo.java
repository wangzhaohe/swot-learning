//@+leo-ver=5-thin
//@+node:swot.20250825194612.1: * @file src/main/java/com/tjise/aop/AdviceTypeAspectDemo.java
//@@language java
//@+others
//@+node:swot.20250825195413.1: ** @ignore-node import
//@@language java
package com.tjise.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
//@+node:swot.20250825195440.1: ** @no-head public class AdviceTypeAspectDemo
//@+doc
// [source,java,linenum]
// ----
//@@c
//@@language java
// 注释下面两个注解，此切面类就失效了
// @Component
// @Aspect
@Slf4j
public class AdviceTypeAspectDemo {  // 切面类
    //@+others
    //@+node:swot.20250825195033.1: *3* @ignore-tree Advice
    //@+node:swot.20250825194612.2: *4* @Around 环绕通知 -> 最常用
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Around("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")
    public Object getTime(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around 前");
        Object obj = joinPoint.proceed();
        System.out.println("around 后");
        return obj;
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // @Around
    //
    // * 此注解标注的通知方法在目标方法前、后都有执行代码。
    // * 此注解修饰的方法必须返回 Object。
    // * @Around 需要自己调用 ProceedingJoinPoint.proceed() 来让目标方法执行，其他通知不需要考虑目标方法执行。
    //@+node:swot.20250825194612.3: *4* @Before -> 了解
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Before("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")
    public void before() {
        System.out.println("before");
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // @Before：此注解标注的通知方法在目标方法前被执行。
    //@+node:swot.20250825194612.4: *4* @After -> 了解
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @After("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")
    public void after() {
        System.out.println("after");
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // @After ：此注解标注的通知方法在目标方法后被执行，无论是否有异常。
    //@+node:swot.20250825194612.5: *4* @AfterReturning -> 了解
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @AfterReturning("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")
    public void afterReturning() {
        System.out.println("afterReturning");
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // @AfterReturning：此注解标注的通知方法在目标方法后被执行，有异常不会执行。
    //@+node:swot.20250825194612.6: *4* @AfterThrowing -> 了解
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @AfterThrowing("execution(* com.tjise.service.impl.DeptServiceImpl.*(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // @AfterThrowing：此注解标注的通知方法发生异常后执行。
    //@-others
}
//@@language asciidoc
//@+doc
// ----
//@+node:swot.20250825195313.1: ** @ignore-node 使用 httpie 测试
//@@language asciidoc
//@+doc
// [source,console]
// ----
// TOKEN=$(http POST :8080/login username=limuwan password=123456 | jq -r '.data')
// http :8080/depts "token: $TOKEN"
// ----
//
// .测试结果：只有 afterThrowing 没有打印，因为没有异常发生。
// ....
// around 前
// before
// 显示其他内容
// afterReturning
// after
// around 后
// ....
//
// 思考：加个运行时异常会怎么样呢？::
// 比如在 DeptServiceImpl 类的 selectAllDept() 方法中加入 `System.out.println(3 / 0);`
//
// .测试结果：只有 afterReturning 不有打印，因为有异常发生了
// ....
// around 前
// before
// afterThrowing
// after
// ....
//@-others
//@-leo
