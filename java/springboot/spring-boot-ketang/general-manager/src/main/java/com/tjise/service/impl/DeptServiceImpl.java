//@+leo-ver=5-thin
//@+node:swot.20241031174225.1: * @file src/main/java/com/tjise/service/impl/DeptServiceImpl.java
//@@language java
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
}
//@+doc
// ----
//@-leo
