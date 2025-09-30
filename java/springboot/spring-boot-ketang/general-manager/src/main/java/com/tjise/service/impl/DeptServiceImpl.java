//@+leo-ver=5-thin
//@+node:swot.20250826094651.1: * @file src/main/java/com/tjise/service/impl/DeptServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250826094651.2: ** @ignore-node import
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
//@+node:swot.20250826094651.3: ** @ignore-node DeptServiceImpl
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
    //@+node:swot.20250826094651.4: *3* @ignore-tree
    //@+node:swot.20250826094651.9: *4* 获取单个部门 -> 计算执行耗时
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
    //@+node:swot.20250826094651.5: *4* 新增部门
    @Override
    public void insertDept(Dept dept) {
        // 指定 createTime & updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 方法调用不用写类型 Dept
        deptMapper.insertDept(dept);
    }
    //@+node:swot.20250826094651.6: *4* 修改部门
    @Override
    public void updateDept(Dept dept) {
        // 补全属性
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
    //@+node:swot.20250826094651.8: *4* 查询部门列表 -> 计算执行耗时
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
    //@+node:swot.20250826094651.7: *3* deleteDeptById 删除单个部门及其员工
    //@+doc
    // [source,java,linenum]
    // ----
    //@@c
    //@@language java
    @Log  // <1>
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteDeptById(Integer id) throws Exception {
        deptMapper.deleteDeptById(id);     // 删除部门
        int delInt = empService.deleteEmpByDeptId(id);  // 把此部门的员工删除
        System.out.println(3 / 0);         // 运行时异常
        return delInt;
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // <1> 给方法增加自定义注解 @Log，让 @annotation() 切点表达式可以找到该方法。
    //
    // .使用 httpie 测试，查看后端日志
    // [source,console]
    // ----
    // TOKEN=$(http POST :8080/login username=limuwan password=123456 | jq -r '.data')
    // http DELETE :8080/depts/1 "token: $TOKEN"  # 有 @Log 注解，会打印 before
    // http :8080/depts "token: $TOKEN"           # 不会打印 before
    // http :8080/depts/1 "token: $TOKEN"         # 不会打印 before
    // ----
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
