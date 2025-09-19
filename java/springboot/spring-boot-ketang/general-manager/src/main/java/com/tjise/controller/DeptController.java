//@+leo-ver=5-thin
//@+node:swot.20250919143736.1: * @file src/main/java/com/tjise/controller/DeptController.java
//@@language java
//@+others
//@+node:swot.20250919143948.1: ** @ignore-node import
package com.tjise.controller;

import com.tjise.pojo.Dept;
import com.tjise.pojo.Result;
import com.tjise.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
//@+node:swot.20250919143959.1: ** @ignore-node class DeptController
@RestController
@RequestMapping("/depts")                         // 抽取到类的注解上
public class DeptController {
    // DI 注入 IOC 容器中的 DeptService Bean 对象，默认名称为 deptService
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result selectAllDept() {

        // 调用 service 层
        List<Dept> deptList = deptService.selectAllDept();

        // 把数据封装成统一的响应格式进行返回
        return Result.success(deptList);
    }
    //@+others
    //@+node:swot.20250919143836.1: *3* deleteDeptById
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @DeleteMapping("/{id}")
    public Result deleteDeptById(@PathVariable Integer id) {

        // 调用 service 层
        int rowsAffected = deptService.deleteDeptById(id);

        // 根据删除结果返回统一的响应格式
        if (rowsAffected > 0) {
            // 删除成功
            return Result.success();
        } else {
            // 删除失败，ID不存在
            return Result.error("部门ID不存在");
        }
    }
    //@+doc
    // ----
    //
    //@-others
    @PostMapping
    public Result insertDept(@RequestBody Dept dept) {
    
        // 前端传入 JSON 数据 { "name": "人事部" }，打印封装的数据只有 name，如下
        // Dept(id=null, name=人事部, createTime=null, updateTime=null)
        // System.out.println(dept);
        
        // 调用 service 层
        deptService.insertDept(dept);

        // 把数据封装成统一的响应格式进行返回
        return Result.success();
    }   
}
//@-others
//@-leo
