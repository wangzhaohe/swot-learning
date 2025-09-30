//@+leo-ver=5-thin
//@+node:swot.20250826094005.1: * @file src/main/java/com/tjise/annotation/Log.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // <2>
@Retention(RetentionPolicy.RUNTIME)  // <3>
public @interface Log {}  // <1>
//@+doc
// ----
//
// <1> 自定义标记性注解，用于记录日志。
//
// <2> `@Target(ElementType.METHOD)`
// +
// - 指定注解可以应用的目标元素类型
// - `ElementType.METHOD` 表示该注解只能用于方法上
// - 如果不写，注解默认可以用于任何元素类型
//
// <3> `@Retention(RetentionPolicy.RUNTIME)`
// +
// - 指定注解的保留策略
// - `RetentionPolicy.RUNTIME` 表示注解在运行时仍然可用，可以通过反射获取
// - 如果不写，默认是 `CLASS` 策略，运行时无法通过反射获取
//
// IMPORTANT: 在 AOP 场景中，@Before、@After 等注解都需要在运行时被 Spring AOP 框架通过反射读取，所以必须使用 RetentionPolicy.RUNTIME 策略。
//@-leo
