//@+leo-ver=5-thin
//@+node:swot.20241031090121.6: * @file src/main/java/com/tjise/service/impl/EmpServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250816114028.1: ** @ignore-node import
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//@+doc
// ----
//
//@+node:swot.20250816114110.1: ** @ignore-node public class EmpServiceImpl
@Service  // 把该类的对象交给 IOC 容器管理
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    //@+others
    //@+node:swot.20250817063940.1: *3* @ignore-tree
    //@+node:swot.20241104151414.1: *4* 分页多条件查询
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
    //@+node:swot.20241104155215.1: *4* 删除员工 EmpServiceImpl
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
    //@+node:swot.20241104160622.1: *4* 新增员工 EmpServiceImpl
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
    //@+node:swot.20241230135844.5: *4* getEmpById
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
    //@+node:swot.20241230135844.10: *4* updateEmp
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
    //@+node:swot.20250816113556.1: *3* 员工登录功能 login -> New Add
    //@@language java
    //@+doc
    // * @param emp 包含用户输入的用户名和密码的 Emp 对象
    //
    // [source,java]
    // ----
    //@@c
    @Override
    public Emp login(Emp emp) {
        Emp e = empMapper.login(emp);
        return e;
    }
    //@+doc
    // ----
    //@-others
}
//@-others
//@-leo
