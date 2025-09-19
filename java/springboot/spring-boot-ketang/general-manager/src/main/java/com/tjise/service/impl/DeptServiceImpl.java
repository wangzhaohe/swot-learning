//@+leo-ver=5-thin
//@+node:swot.20250919084938.1: * @file src/main/java/com/tjise/service/impl/DeptServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250919085114.1: ** @ignore-node class DeptServiceImpl
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.service.impl;

import com.tjise.mapper.DeptMapper;
import com.tjise.pojo.Dept;
import com.tjise.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service  // 把该类的对象交给 IOC 容器管理
public class DeptServiceImpl implements DeptService {

    // DI 注入 DeptMapper 实现类对象给变量 deptMapper
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> selectAllDept() {
        List<Dept> deptList = deptMapper.selectAllDept();
        return deptList;
    }
    //@+others
    //@+node:swot.20241101091510.1: *3* deleteDeptById
    //@@language java
    //@+doc
    // .src/main/java/com/tjise/service/impl/DeptServiceImpl.java
    // [source,java,linenums]
    // ----
    //@@c
    @Override
    public void deleteDeptById(Integer id) {
        // 方法调用不用写类型 Integer
        deptMapper.deleteDeptById(id);
    }
    //@+doc
    // ----
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
