//@+leo-ver=5-thin
//@+node:swot.20250919162317.1: * @file src/main/java/com/tjise/controller/DeptController.java
//@@language java
//@+others
//@+node:swot.20250919162317.2: ** @ignore-node import
package com.tjise.controller;

import com.tjise.pojo.Dept;
import com.tjise.pojo.Result;
import com.tjise.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
//@+node:swot.20250919162317.3: ** @ignore-node class DeptController
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
    //@+node:swot.20250919162317.4: *3* @ignore-node deleteDeptById
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
    //@+node:swot.20250919162317.5: *3* @ignore-node getDeptById
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @GetMapping("/{id}")
    public Result getDeptById(@PathVariable Integer id){
        Dept dept = deptService.getDeptById(id);
        return Result.success(dept);
    }
    //@+doc
    // ----
    //@+node:swot.20241104091227.2: *3* updateDept
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        deptService.updateDept(dept);
        // 这儿的逻辑有些粗糙，应该判断修改是否成功（此处省略）
        return Result.success();
    }
    //@+doc
    // ----
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
