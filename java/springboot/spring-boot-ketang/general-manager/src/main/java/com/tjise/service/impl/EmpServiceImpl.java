//@+leo-ver=5-thin
//@+node:swot.20250826221444.1: * @file src/main/java/com/tjise/service/impl/EmpServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250826221444.2: ** @ignore-node import
//@@language java
//@+doc
// [source,java]
// ----
//@@c
package com.tjise.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tjise.annotation.Log;
import com.tjise.mapper.EmpMapper;
import com.tjise.pojo.Emp;
import com.tjise.pojo.PageBean;
import com.tjise.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//@+doc
// ----
//
//@+node:swot.20250826221444.3: ** @ignore-node public class EmpServiceImpl
@Service  // 把该类的对象交给 IOC 容器管理
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    //@+others
    //@+node:swot.20250826221444.4: *3* @ignore-tree
    //@+node:swot.20250826221444.5: *4* 分页多条件查询
    @Override
    public PageBean selectPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {

        /*
        Long total = empMapper.selectCount();

        int offset = (page - 1) * pageSize;
        List<Emp> empList = empMapper.selectPage(offset, pageSize);
        PageBean pageBean = new PageBean(total, empList);
        return pageBean;
         */

        PageHelper.startPage(page, pageSize);
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageBean(p.getTotal(), p.getResult());
    }
    //@+node:swot.20250826221444.8: *4* 查询单个员工 EmpServiceImpl
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    public Emp getEmpById(Integer id) {
        Emp emp = empMapper.getEmpById(id);
        return emp;
    }
    //@+doc
    // ----
    //
    //@+node:swot.20250826221444.10: *4* 员工登录功能 login -> 不要加 @Log 因为没登录无法获取操作者
    //@+doc
    // * @param emp 包含用户输入的用户名和密码的 Emp 对象
    //
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Override
    public Emp login(Emp emp) {
        Emp e = empMapper.login(emp);
        return e;
    }
    //@+doc
    // ----
    //@+node:swot.20250826221444.7: *3* insertEmp 新增员工
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @Log  // <1>
    @Override
    public void insertEmp(Emp emp){
        // 补全属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insertEmp(emp);
    }
    //@+doc
    // ----
    //
    // <1> 给方法增加自定义注解，让 @annotation() 切点表达式可以找到该方法。
    //@+node:swot.20250826221444.9: *3* updateEmp 修改单个员工
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    // emp 封装了要修改的数据
    @Log  // <1>
    @Override
    public void updateEmp(Emp emp) {
        // 在前端提交的表单中没有更新时间，所以需要后台来补充此属性
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);
    }
    //@+doc
    // ----
    //
    // <1> 给方法增加自定义注解，让 @annotation() 切点表达式可以找到该方法。
    //@+node:swot.20250826221444.6: *3* deleteEmpByIds 删除员工
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    @Log  // <1>
    @Override
    public void deleteEmpByIds(List<Integer> ids) {
        empMapper.deleteEmpByIds(ids);
    }
    //@+doc
    // ----
    //
    // <1> 给方法增加自定义注解，让 @annotation() 切点表达式可以找到该方法。
    //@+node:swot.20250826221444.11: *3* deleteEmpByDeptId 根据部门 id 删除员工
    //@+doc
    // [source,java,linenum]
    // ----
    //@@c
    //@@language java
    @Transactional
    @Log  // <1>
    @Override
    public int deleteEmpByDeptId(Integer deptId) {
        return empMapper.deleteEmpByDeptId(deptId);
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // <1> 给方法增加自定义注解，让 @annotation() 切点表达式可以找到该方法。
    //@-others
}
//@-others
//@-leo
