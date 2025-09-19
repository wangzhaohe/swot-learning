//@+leo-ver=5-thin
//@+node:swot.20250919162323.1: * @file src/main/java/com/tjise/service/DeptService.java
//@@language java
//@+doc
// 定义该接口目的是为了用类的多态实现 controller 层左边解耦。
//
// [source,java,linenums]
// ----
//@@c
package com.tjise.service;

import com.tjise.pojo.Dept;
import java.util.List;

public interface DeptService {
    public abstract List<Dept> selectAllDept();

    // 抽象方法：返回删除的影响行数 int，用于判断是否删除成功
    public abstract int deleteDeptById(Integer id);

    public abstract void insertDept(Dept dept);
    
    public abstract Dept getDeptById(Integer id);
    
    // 新增更新单个部门
    public abstract void updateDept(Dept dept);
}
//@+doc
// ----
//@-leo
