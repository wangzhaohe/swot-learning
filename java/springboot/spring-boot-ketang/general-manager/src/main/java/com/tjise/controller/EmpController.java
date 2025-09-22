//@+leo-ver=5-thin
//@+node:swot.20250922113452.3: * @file src/main/java/com/tjise/controller/EmpController.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.controller;

import com.tjise.pojo.PageBean;
import com.tjise.pojo.Result;
import com.tjise.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    //@+others
    //@+node:swot.20241104140344.1: ** selectPage
    //@+doc
    // .多条件分页查询
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    /* @RequestParam(default) 设置默认值
       若不设默认值，且前端又没传递参数 page 和 pageSize
       则在 service 层对 page 做运算时会报空值异常(NullPointerException) */
    @GetMapping
    public Result selectPage(
            @RequestParam(defaultValue="1")  Integer page,
            @RequestParam(defaultValue="10") Integer pageSize,
            // 多传递了 4 个参数
            String name,
            Short gender,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end)
    {
        // 打印看是否可以取到前端传递的参数，也可以使用 IDEA debug 模式查看
        System.out.printf(
            "page=%s, pageSize=%s, name=%s, gender=%s, begin=%s, end=%s%n",
             page, pageSize, name, gender, begin, end);

        PageBean pageBean = empService.selectPage(
                            page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }
    //@+doc
    // ----
    //@-others
}
//@+doc
// ----
//@-leo
