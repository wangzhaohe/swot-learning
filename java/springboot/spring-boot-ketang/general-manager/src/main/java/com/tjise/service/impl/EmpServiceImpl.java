//@+leo-ver=5-thin
//@+node:swot.20250922105712.1: * @file src/main/java/com/tjise/service/impl/EmpServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250922112029.1: ** @ignore-node import
package com.tjise.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tjise.mapper.EmpMapper;
import com.tjise.pojo.Emp;
import com.tjise.pojo.PageBean;
import com.tjise.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//@+node:swot.20250922110410.1: ** @ignore-node class EmpServiceImpl
//@+doc
// [source,java,linenums]
// ----
//@@c
@Service  // 把该类的对象交给 IOC 容器管理
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    //@+others
    //@+node:swot.20250922105712.2: *3* selectPage
    //@+doc
    // 获取总记录数和当前页数据，封装成 PageBean 返回。
    //
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    @Override
    public PageBean selectPage(Integer page, Integer pageSize) {

        /*
        // 获取总记录数
        Long total = empMapper.selectCount();

        // 获取当前页数据
        int offset = (page - 1) * pageSize;  // <1>
        List<Emp> empList = empMapper.selectPage(offset, pageSize);

        // 封装成 PageBean
        PageBean pageBean = new PageBean(total, empList);
        return pageBean;
        */
        // 下面节点内容放在此处
        //@+others
        //@+node:swot.20241104135605.1: *4* 使用 PageHelper 的逻辑
        //@+doc
        // [source,java,linenums]
        // ----
        //@@c
        //@@language java
        // 设置分页查询参数 page: 页码, pageSize: 每页显示数量
        PageHelper.startPage(page, pageSize);   // 紧跟着的第一个 select 方法会被分页 <1>
        List<Emp> empList = empMapper.list();   // 执行查询  // <2>
        Page<Emp> p = (Page<Emp>) empList;      // 获取分页结果，将 empList 转成 Page 类型

        // 封装成 PageBean 对象返回
        return new PageBean(p.getTotal(), p.getResult());
        //@@language asciidoc
        //@+doc
        // ----
        //
        // <1> 必须紧跟着！！！
        // <2> PageHelper 已经帮你做了物理分页，不必担心一次性查全表的问题。
        //@-others
    }
    //@@language asciidoc
    //@+doc
    // ----
    //@@wrap
    // <1> 在 controller 层对 page 设置了默认值，所以即使前端没传递 page 和 pageSize，此处也不会报 NullPointerException。
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
