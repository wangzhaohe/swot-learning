//@+leo-ver=5-thin
//@+node:swot.20250919143443.1: * @file src/main/java/com/tjise/service/impl/DeptServiceImpl.java
//@@language java
//@+others
//@+node:swot.20250919143443.2: ** @ignore-node class DeptServiceImpl
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

import java.time.LocalDateTime;
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
    //@+node:swot.20250919143443.3: *3* deleteDeptById
    //@@language java
    //@+doc
    // .src/main/java/com/tjise/service/impl/DeptServiceImpl.java
    // [source,java,linenums]
    // ----
    //@@c
    @Override
    public int deleteDeptById(Integer id) {
        // 方法调用不用写类型 Integer
        return deptMapper.deleteDeptById(id);
    }
    //@+doc
    // ----
    //@+node:swot.20250919143443.4: *3* @ignore-node  insertDept
    //@@language java
    //@+doc
    // 数据库表 dept 字段 对应的 java 实体类中有 4 个属性
    //
    // ```java
    // public class Dept {
    //     private Integer id;
    //     private String name;
    //     private LocalDateTime createTime;
    //     private LocalDateTime updateTime;
    // }
    // ```
    //
    // * id 是数据库自增长字段
    // * name 是前端传送来的
    // * createTime 需要后端指定
    // * updateTime 需要后端指定
    //
    // [source,java,linenums]
    // ----
    //@@c
    @Override
    public void insertDept(Dept dept) {
        // 指定 createTime & updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 方法调用不用写类型 Dept
        deptMapper.insertDept(dept);
    }
    //@+doc
    // ----
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
