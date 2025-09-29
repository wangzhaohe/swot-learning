//@+leo-ver=5-thin
//@+node:swot.20250824163536.1: * @file src/main/java/com/tjise/service/impl/EmpServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250824163536.2: ** @ignore-node import
//@@language java
//@+doc
// [source,java]
// ----
//@@c
package com.tjise.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
//@+node:swot.20250824163536.3: ** @ignore-node public class EmpServiceImpl
@Service  // 把该类的对象交给 IOC 容器管理
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    //@+others
    //@+node:swot.20250824163536.4: *3* @ignore-tree
    //@+node:swot.20250824163536.5: *4* 分页多条件查询
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
    //@+node:swot.20250824163536.6: *4* 删除员工 EmpServiceImpl
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @Override
    public void deleteEmpByIds(List<Integer> ids) {
        empMapper.deleteEmpByIds(ids);
    }
    //@+doc
    // ----
    //@+node:swot.20250824163536.7: *4* 新增员工 EmpServiceImpl
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @Override
    public void insertEmp(Emp emp){
        // 补全属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insertEmp(emp);
    }
    //@+doc
    // ----
    //@+node:swot.20250824163536.8: *4* 查询单个员工 EmpServiceImpl
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
    //@+node:swot.20250824163536.9: *4* 修改单个员工 EmpServiceImpl
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    // emp 封装了要修改的数据
    public void updateEmp(Emp emp) {
        // 在前端提交的表单中没有更新时间，所以需要后台来补充此属性
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);
    }
    //@+doc
    // ----
    //
    //@+node:swot.20250824163536.10: *4* 员工登录功能 login
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
    //@+node:swot.20250824163536.11: *3* deleteEmpByDeptId 根据部门 id 删除员工 -> REQUIRED vs REQUIRES_NEW
    //@+doc
    // [source,java,linenum]
    // ----
    //@@c
    //@@language java
    // 此例子中不加 @Transactional 也会回滚，因为 deleteEmpByDeptId(id) 已经被 deleteDeptById() 的事务包含了。

    // @Transactional  // <1>
    // @Transactional(propagation = Propagation.REQUIRED)  // 这是默认值，同上  // <1>
    @Transactional(propagation = Propagation.REQUIRES_NEW)  // <2>
    @Override
    public int deleteEmpByDeptId(Integer deptId) {
        return empMapper.deleteEmpByDeptId(deptId);
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // 删除员工也增加 @Transactional 进行事务管理。
    //
    // <1> 【默认值】有事务则加入，无则创建新事务。
    // +
    // * 与调用该方法的 deleteDeptById() 是同一个事务，当 deleteDeptById() 中有异常时该方法##会回滚##。
    //
    // <2> 无论有无，总是创建新事务。
    // +
    // * 与调用该方法的 deleteDeptById() 不是同一个事务了，当 deleteDeptById() 中有异常时该方法##不会回滚##。
    //
    // NOTE: 可以对比 Spring Boot 日志进行核实。
    //
    // [WARNING]
    // ====
    // 在这里只是演示 propagation 取值的用法。
    //
    // 本例中该方法正确的逻辑应该是不写 @Transactional 或者使用 @Transactional(propagation = Propagation.REQUIRED)。因为删除部门不成功，应该回滚删除员工的操作。
    // ====
    //@-others
}
//@-others
//@-leo
