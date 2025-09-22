//@+leo-ver=5-thin
//@+node:swot.20250922113542.1: * @file src/main/java/com/tjise/service/impl/EmpServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250922113542.2: ** @ignore-node import
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
import java.util.List;
//@+node:swot.20250922113542.3: ** @ignore-node class EmpServiceImpl
//@+doc
// [source,java,linenums]
// ----
//@@c
@Service  // 把该类的对象交给 IOC 容器管理
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    //@+others
    //@+node:swot.20241104140358.1: *3* 多条件查询传递多个参数
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
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
