//@+leo-ver=5-thin
//@+node:swot.20250824154850.1: * @file src/main/java/com/tjise/service/DeptService.java
//@@language java
//@+others
//@+node:swot.20250824154850.3: ** DeptService
//@@language java
//@+doc
// .src/main/java/com/tjise/service/DeptService.java
// [source,java,linenums]
// ----
//@@c
package com.tjise.service;

import com.tjise.pojo.Dept;
import java.util.List;

public interface DeptService {

    public abstract List<Dept> selectAllDept();

    public abstract int deleteDeptById(Integer id) throws Exception;  // <1>

    public abstract void insertDept(Dept dept);

    public abstract Dept getDeptById(Integer id);

    public abstract void updateDept(Dept dept);
}
//@+doc
// ----
//
// <1> 对应于 src/main/java/com/tjise/service/impl/DeptServiceImpl.java，方法中也要声明一下 throws Exception，否则编译无法通过。
//@-others

//@-leo
