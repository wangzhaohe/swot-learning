//@+leo-ver=5-thin
//@+node:swot.20250922092133.1: * @file src/main/java/com/tjise/controller/EmpController.java
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    //@+others
    //@+node:swot.20250922093737.1: ** selectPage
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @GetMapping
    public Result selectPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = empService.selectPage(page, pageSize);
        return Result.success(pageBean);
    }
    //@+doc
    // ----
    //@@wrap
    // * 分页查询，先不考虑条件查询。
    // * @RequestParam(default) 设置 page 和 pageSize 默认值。
    // * 若不设默认值，且前端又没传递参数 page 和 pageSize，则在 service 层对 page 做运算时会报空值异常(NullPointerException)。
    //@-others
}
//@+doc
// ----
//@-leo
