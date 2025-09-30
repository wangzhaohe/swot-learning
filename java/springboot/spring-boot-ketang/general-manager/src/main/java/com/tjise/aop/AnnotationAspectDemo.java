//@+leo-ver=5-thin
//@+node:swot.20250826095516.1: * @file src/main/java/com/tjise/aop/AnnotationAspectDemo.java
//@+doc
// [source,java,linenum]
// ----
//@@c
//@@language java
package com.tjise.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AnnotationAspectDemo {

    // @Before：此注解标注的方法，在目标方法前被执行。
    @Before("@annotation(com.tjise.annotation.Log)")  // <1>
    public void before() {
        System.out.println("before use @annootation by @Log");
    }
}
//@+doc
// ----
//
// <1> 使用 @annotation() 切点表达式来 *根据目标方法是否有注解* 来进行匹配。
//@-leo
