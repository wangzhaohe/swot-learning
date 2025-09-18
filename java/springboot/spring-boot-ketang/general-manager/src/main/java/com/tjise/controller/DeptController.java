//@+leo-ver=5-thin
//@+node:swot.20241031174034.1: * @file src/main/java/com/tjise/controller/DeptController.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.controller;

import com.tjise.pojo.Dept;
import com.tjise.pojo.Result;
import com.tjise.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
//@+doc
// ----
//@-leo
