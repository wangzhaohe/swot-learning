//@+leo-ver=5-thin
//@+node:swot.20250919102245.1: * @file src/main/java/com/tjise/controller/DeptController.java
//@@language java
//@+others
//@+node:swot.20250919102245.2: ** @ignore-node class DeptController
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.controller;

import com.tjise.pojo.Dept;
import com.tjise.pojo.Result;
import com.tjise.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 如果是浏览器访问，会有跨域问题，参下面节点的解决方式
@RestController
public class DeptController {

    @Autowired        // DI 注入 IOC 容器中的 DeptService Bean 对象，默认名称为 deptService
    private DeptService deptService;

//  @RequestMapping("/depts")                                        // 所有方法都可以请求
//  @RequestMapping(value = "/depts", method = {RequestMethod.GET})  // 完整写法
    @GetMapping("/depts")                                            // 更加简洁的方式
    public Result selectAllDept(){
        List<Dept> deptList = deptService.selectAllDept();
        return Result.success(deptList);
    }
    //@+others
    //@+node:swot.20250919102245.3: *3* @ignore-node deleteDeptById
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @DeleteMapping("/depts/{id}")  // 路径参数
    public Result deleteDeptById(@PathVariable Integer id){
        // 调用 service 层去删除数据库记录
        deptService.deleteDeptById(id);
        return Result.success();
    }
    //@+doc
    // ----
    //@+node:swot.20241101144418.2: *3* insertDept
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @PostMapping("/depts")
    public Result insertDept(@RequestBody Dept dept){
        // 调用 service 层去删除数据库记录
        deptService.insertDept(dept);
        return Result.success();
    }
    //@+doc
    // ----
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
