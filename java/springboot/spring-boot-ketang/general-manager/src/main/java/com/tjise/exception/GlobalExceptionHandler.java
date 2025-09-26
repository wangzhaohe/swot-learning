//@+leo-ver=5-thin
//@+node:swot.20250823220337.1: * @file src/main/java/com/tjise/exception/GlobalExceptionHandler.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.exception;

import com.tjise.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

 // 全局异常处理器
@RestControllerAdvice  // <1>
public class GlobalExceptionHandler {

    // @ExceptionHandler(value = {Exception.class})
    @ExceptionHandler(Exception.class)  // 简写形式，此方法可以捕获所有异常
    public Result handleException(Exception e)  // 会把拦截的异常传递给此方法的参数 e
    {
        e.printStackTrace();  // 只是打印了异常信息
        //return Result.error(e.getMessage());  // 如有需要可以自定义错误信息，本例子省略。
        // 上面信息太多字符了，用户看不懂，所以只返回一个通用的错误信息
        return Result.error("处理异常，请稍后重试...");
    }
}
//@+doc
// ----
//
// <1> @RestControllerAdvice 注解说明：
//
// - @RestControllerAdvice 是 @ControllerAdvice + @ResponseBody 的组合注解
// - 让这个类成为全局异常处理器
// - 使得所有异常处理方法返回的结果直接作为 JSON 响应
//
// Exception 异常分类
//
// *  运行时异常 : RuntimeException, 编译时无需处理
// *  编译时异常 : 非 RuntimeException, 编译时处理
//@-leo
