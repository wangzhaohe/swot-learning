//@+leo-ver=5-thin
//@+node:swot.20250919084644.1: * @file src/main/java/com/tjise/service/DeptService.java
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

    // 新增抽象方法：无返回值，只传入要删除记录的数据库 id 即可。
    public abstract void deleteDeptById(Integer id);
}
//@+doc
// ----
//@-leo
