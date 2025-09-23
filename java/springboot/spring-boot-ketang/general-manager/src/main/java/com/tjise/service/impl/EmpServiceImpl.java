//@+leo-ver=5-thin
//@+node:swot.20250923083510.1: * @file src/main/java/com/tjise/service/impl/EmpServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250923083510.2: ** @ignore-node import
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
//@+node:swot.20250923083510.3: ** @ignore-node class EmpServiceImpl
//@+doc
// [source,java,linenums]
// ----
//@@c
@Service  // 把该类的对象交给 IOC 容器管理
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    //@+others
    //@+node:swot.20250923083510.4: *3* @ignore-node selectPage 多条件查询传递多个参数
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    @Override
    public PageBean selectPage(Integer page,
                               Integer pageSize,
                               String name,
                               Short gender,
                               LocalDate begin,
                               LocalDate end)
    {
        // 设置分页查询参数 pageNum: 页码, pageSize: 每页显示数量
        PageHelper.startPage(page, pageSize);   // 紧跟着的第一个select方法会被分页
        List<Emp> empList = empMapper.list(name, gender, begin, end);   // 执行查询
        Page<Emp> p = (Page<Emp>) empList;      // 获取分页结果，将 empList 转成 Page 类型

        // 封装成 PageBean 对象返回
        return new PageBean(p.getTotal(), p.getResult());
    }
    //@+doc
    // ----
    //
    // <1> 必须紧跟着！！！
    // <2> PageHelper 已经帮你做了物理分页，不必担心一次性查全表的问题。
    //@+node:swot.20250923083510.5: *3* @ignore-node deleteEmpByIds
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
    //@+node:swot.20241104152833.4: *3* insertEmp
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @Override
    public void insertEmp(Emp emp){
        // 业务逻辑补全属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insertEmp(emp);
    }
    //@+doc
    // ----
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
