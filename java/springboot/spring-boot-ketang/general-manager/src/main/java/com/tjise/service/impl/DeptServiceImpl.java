//@+leo-ver=5-thin
//@+node:swot.20250825165833.1: * @file src/main/java/com/tjise/service/impl/DeptServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250825165833.2: ** @ignore-node import
package com.tjise.service.impl;

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
//@+node:swot.20250825165833.3: ** @ignore-node DeptServiceImpl
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
    //@+node:swot.20250825165833.4: *3* @ignore-tree
    //@+node:swot.20250825165833.5: *4* 新增部门
    @Override
    public void insertDept(Dept dept) {
        // 指定 createTime & updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 方法调用不用写类型 Dept
        deptMapper.insertDept(dept);
    }
    //@+node:swot.20250825165833.6: *4* 修改部门
    @Override
    public void updateDept(Dept dept) {
        // 补全属性
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
    //@+node:swot.20250825165833.7: *4* deleteDeptById 删除单个部门及其员工
    //@+doc
    // [source,java,linenum]
    // ----
    //@@c
    //@@language java
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteDeptById(Integer id) throws Exception {

        deptMapper.deleteDeptById(id);     // 删除部门
        int delInt = empService.deleteEmpByDeptId(id);  // 把此部门的员工删除  // <2>
        System.out.println(3 / 0);         // 运行时异常  // <1>
        return delInt;
    }
    //@@language asciidoc
    //@+doc
    // ----
    // <1> 当产生该运行时异常时，看 <2> 的回滚情况。
    //
    // <2> deleteEmpByDeptId(id) 方法的注解 @Transactional(propagation) 取值不同，会影响 deleteEmpByDeptId(id) 是否回滚。
    // +
    // * @Transactional(propagation = Propagation.REQUIRED) 会回滚
    // * @Transactional(propagation = Propagation.REQUIRES_NEW) 不会回滚
    // * deleteEmpByDeptId(id) 不加 @Transactional 也会回滚，因为 deleteEmpByDeptId(id) 已经被 deleteDeptById() 的事务包含了。
    //@+node:swot.20250825165833.8: *3* 查询部门列表 selectAllDept -> 计算执行耗时
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Override
    public List<Dept> selectAllDept() {

        // long begin = System.currentTimeMillis();  // <1>

        List<Dept> deptList = deptMapper.selectAllDept();

        // long end = System.currentTimeMillis();  // <2>
        // log.info("方法执行耗时: {} ms", (end-begin));  // <3>

        return deptList;
    }
    //@+doc
    // ----
    //
    // <1> 开始时间
    // <2> 结束时间
    // <3> 计算耗时
    //
    // NOTE: @Slf4j 来自于于 lombok 依赖，包含了记录日志的方法 log。
    //@+node:swot.20250825165833.9: *3* 获取单个部门 getDeptById -> 计算执行耗时
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
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
