//@+leo-ver=5-thin
//@+node:swot.20250824071548.1: * @file src/main/java/com/tjise/service/impl/DeptServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250824071548.2: ** @ignore-node import
package com.tjise.service.impl;

import com.tjise.mapper.DeptMapper;
import com.tjise.pojo.Dept;
import com.tjise.service.DeptService;
import com.tjise.service.EmpService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
//@+node:swot.20250824071548.3: ** DeptServiceImpl -> 为了删除员工新增 DI empService
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
@Service  // 把该类的对象交给 IOC 容器管理
public class DeptServiceImpl implements DeptService {

    // DI 注入 DeptMapper 实现类对象给变量 deptMapper
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpService empService;  // New Added
    //@+others
    //@+node:swot.20250824071740.1: *3* @ignore-tree
    //@+node:swot.20250824071548.4: *4* 查询部门列表
    @Override
    public List<Dept> selectAllDept() {
        List<Dept> deptList = deptMapper.selectAllDept();
        return deptList;
    }
    //@+node:swot.20250824071548.6: *4* 新增部门
    @Override
    public void insertDept(Dept dept) {
        // 指定 createTime & updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 方法调用不用写类型 Dept
        deptMapper.insertDept(dept);
    }
    //@+node:swot.20250824071548.7: *4* 获取单个部门
    @Override
    public Dept getDeptById(Integer id) {
        Dept dept = deptMapper.getDeptById(id);
        return dept;
    }
    //@+node:swot.20250824071548.8: *4* 修改部门
    @Override
    public void updateDept(Dept dept) {
        // 补全属性
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
    //@+node:swot.20250824071548.5: *3* deleteDeptById 删除单个部门及其员工
    //@+doc
    // [caption=]
    // .测试事务: 2 个条件，分为 4 种情况如下表所示
    // [cols="1,2,2",options="header"]
    // |===
    // |@Transactional     | System.out.println(3/0) 异常       | 功能
    // |不加 @Transactional | 也不加 System.out.println(3/0) 异常 | 可以正常删除部门及其下的所有员工
    // |不加 @Transactional | 但是加 System.out.println(3/0) 异常 |可以正常删除部门，但是部门下的所有员工无法删除
    // |加 @Transactional  | 也加 System.out.println(3/0) 异常   |则部门及其下的所有员工不会删除（回滚了）
    // |加 @Transactional  | 不加 System.out.println(3/0) 异常   |则部门及其下的所有员工会被正常删除
    // |===
    //
    // [source,java,linenum]
    // ----
    //@@c
    //@@language java
    @Transactional  // <2>
    @Override
    public int deleteDeptById(Integer id) {
        // 删除部门
        deptMapper.deleteDeptById(id);

        // System.out.println(3/0);  // <1>

        // 把此部门的员工删除
        return empService.deleteEmpByDeptId(id);  // int 类型
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // <1> 如果有此运行时异常，则部门可以被删除，但是该部门员工无法被删除。
    // <2> 增加此注解即可实现事务管理，解决 <1> 的问题。遇见异常可以回滚了。
    // +
    // * 注解：@Transactional
    // * 位置：可放在业务（service）层的方法上、类上、接口上。#实际开发中经常加在方法上。#
    // * 作用：将当前方法交给 spring 进行事务管理。
    // ** 方法执行前开启事务；
    // ** 成功执行完毕，提交事务；
    // ** 出现异常，回滚事务。
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
