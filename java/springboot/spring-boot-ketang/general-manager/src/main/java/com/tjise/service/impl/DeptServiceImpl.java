//@+leo-ver=5-thin
//@+node:swot.20250826215506.1: * @file src/main/java/com/tjise/service/impl/DeptServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250826215506.2: ** @ignore-node import
package com.tjise.service.impl;

import com.tjise.annotation.Log;
import com.tjise.mapper.DeptMapper;
import com.tjise.pojo.Dept;
import com.tjise.service.DeptService;
import com.tjise.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
//@+node:swot.20250826215506.3: ** @ignore-node DeptServiceImpl
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
@Slf4j
@Service  // 把该类的对象交给 IOC 容器管理
public class DeptServiceImpl implements DeptService {

    // DI 注入 DeptMapper 实现类对象给变量 deptMapper
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpService empService;  // 新增
    //@+others
    //@+node:swot.20250826215506.4: *3* @ignore-tree 查询操作不需要记录日志到数据库中
    //@+node:swot.20250826215506.5: *4* 获取单个部门 -> 计算执行耗时
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Override
    public Dept getDeptById(Integer id) {

        // long begin = System.currentTimeMillis();  // <1>

        Dept dept = deptMapper.getDeptById(id);

        // long end = System.currentTimeMillis();  // <2>
        // log.info("方法执行耗时: {} ms", (end-begin));  // <3>

        return dept;
    }
    //@+doc
    // ----
    //
    // <1> 开始时间
    // <2> 结束时间
    // <3> 计算耗时
    //@+node:swot.20250826215506.8: *4* 查询部门列表 -> 计算执行耗时
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Override
    public List<Dept> selectAllDept() {
        List<Dept> deptList = deptMapper.selectAllDept();
        return deptList;
    }
    //@+doc
    // ----
    //@+node:swot.20250826215506.6: *3* insertDept 新增部门 -> 增加 @Log
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Override
    @Log  // <1>
    public void insertDept(Dept dept) {
        // 指定 createTime & updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 方法调用不用写类型 Dept
        deptMapper.insertDept(dept);
    }
    //@+doc
    // ----
    //
    // <1> 给方法增加自定义注解，让 @annotation() 切点表达式可以找到该方法。
    //@+node:swot.20250826215506.7: *3* updateDept 修改部门 -> 增加 @Log
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Override
    @Log  // <1>
    public void updateDept(Dept dept) {
        // 补全属性
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
    //@+doc
    // ----
    //
    // <1> 给方法增加自定义注解，让 @annotation() 切点表达式可以找到该方法。
    //@+node:swot.20250826215506.9: *3* deleteDeptById 删除单个部门及其员工 -> 增加 @Log
    //@+doc
    // [source,java,linenum]
    // ----
    //@@c
    //@@language java
    @Transactional(rollbackFor = Exception.class)
    @Override
    @Log  // <1>
    public int deleteDeptById(Integer id) throws Exception {

        deptMapper.deleteDeptById(id);     // 删除部门
        return empService.deleteEmpByDeptId(id);  // 把此部门的员工删除
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // <1> 给方法增加自定义注解，让 @annotation() 切点表达式可以找到该方法。
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
