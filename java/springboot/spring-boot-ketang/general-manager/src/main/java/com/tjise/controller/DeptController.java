//@+leo-ver=5-thin
//@+node:swot.20250824155158.1: * @file src/main/java/com/tjise/controller/DeptController.java
//@@language java
//@+others
//@+node:swot.20250824155158.2: ** @ignore-node import
package com.tjise.controller;

import com.tjise.pojo.Dept;
import com.tjise.pojo.Result;
import com.tjise.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@+node:swot.20250824155158.3: ** @ignore-node DeptController
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
@RestController
@RequestMapping("/depts")
public class DeptController {
    // DI 注入 IOC 容器中的 DeptService Bean 对象，默认名称为 deptService
    @Autowired
    private DeptService deptService;
    //@+others
    //@+node:swot.20250824155158.4: *3* @ignore-tree 
    //@+node:swot.20250824155158.5: *4* 查询部门列表
    //  @RequestMapping("/depts")                                        // 所有方法都可以请求
    //  @RequestMapping(value = "/depts", method = {RequestMethod.GET})  // 限制只能使用 GET 方法请求
    @GetMapping                                            // 更加简洁的方式
    public Result selectAllDept(){
        List<Dept> deptList = deptService.selectAllDept();
        return Result.success(deptList);
    }
    //@+node:swot.20250824155158.9: *4* 新增单个部门
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @PostMapping
    public Result insertDept(@RequestBody Dept dept){
        deptService.insertDept(dept);
        return Result.success();
    }
    //@+doc
    // ----
    //@+node:swot.20250824155158.7: *4* 获取单个部门
    @GetMapping("/{id}")
    public Result getDeptById(@PathVariable Integer id){
        Dept dept = deptService.getDeptById(id);
        return Result.success(dept);
    }
    //@+node:swot.20250824155158.8: *4* 修改部门
    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        deptService.updateDept(dept);
        return Result.success();
    }
    //@+node:swot.20250824155158.6: *3* deleteDeptById 删除单个部门
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @DeleteMapping("/{id}")  // 路径参数
    public Result deleteDeptById(@PathVariable Integer id) throws Exception {  // <1>
        // 调用 service 层去删除数据库记录
        deptService.deleteDeptById(id);
        return Result.success();
    }
    //@+doc
    // ----
    //
    // <1> 对应于 src/main/java/com/tjise/service/DeptService.java，方法中也要声明一下 throws Exception，否则编译无法通过。
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
