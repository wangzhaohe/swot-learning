//@+leo-ver=5-thin
//@+node:swot.20241031175500.1: * @file src/main/java/com/tjise/service/DeptService.java
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
}
//@+doc
// ----
//@-leo
