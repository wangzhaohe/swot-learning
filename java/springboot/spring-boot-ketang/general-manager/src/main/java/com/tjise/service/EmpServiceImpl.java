//@+leo-ver=5-thin
//@+node:swot.20250922092146.1: * @file src/main/java/com/tjise/service/EmpServiceImpl.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.service;

import com.tjise.mapper.EmpMapper;
import com.tjise.pojo.Emp;
import com.tjise.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // 把该类的对象交给 IOC 容器管理
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    //@+others
    //@+node:swot.20241104134525.1: ** selectPage
    //@+doc
    // 获取总记录数和当前页数据，封装成 PageBean 返回。
    //
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    @Override
    public PageBean selectPage(Integer page, Integer pageSize) {

        // 获取总记录数
        Long total = empMapper.selectCount();

        // 获取当前页数据
        int offset = (page - 1) * pageSize;  // <1>
        List<Emp> empList = empMapper.selectPage(offset, pageSize);

        // 封装成 PageBean
        PageBean pageBean = new PageBean(total, empList);
        return pageBean;
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
//@-leo
