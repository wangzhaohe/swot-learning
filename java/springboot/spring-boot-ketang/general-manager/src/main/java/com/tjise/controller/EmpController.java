//@+leo-ver=5-thin
//@+node:swot.20250922161251.1: * @file src/main/java/com/tjise/controller/EmpController.java
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    //@+others
    //@+node:swot.20250922161251.2: ** @ignore-node selectPage
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
    //@+node:swot.20241104152539.1: ** deleteEmp
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @DeleteMapping("/{ids}")
    public Result deleteEmp(@PathVariable List<Integer> ids) {
        empService.deleteEmpByIds(ids);
        return Result.success();
    }
    //@+doc
    // ----
    //@-others
}
//@+doc
// ----
//@-leo
