//@+leo-ver=5-thin
//@+node:swot.20250924160639.1: * @file src/main/java/com/tjise/service/EmpService.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.service;

import com.tjise.pojo.Emp;
import com.tjise.pojo.PageBean;
import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    //@+others
    //@+node:swot.20250924160639.2: ** @ignore-node selectPage
    public abstract PageBean selectPage(
            Integer page,
            Integer pageSize,
            String name,
            Short gender,
            LocalDate begin,
            LocalDate end);
    //@+node:swot.20250924160639.3: ** @ignore-node deleteEmpByIds
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    void deleteEmpByIds(List<Integer> ids);
    //@+doc
    // ----
    //
    //@+node:swot.20250924160639.4: ** @ignore-node insertEmp
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    void insertEmp(Emp emp);
    //@+doc
    // ----
    //
    //@+node:swot.20250924160639.5: ** @ignore-node getEmpById
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    public abstract Emp getEmpById(Integer id);
    //@+doc
    // ----
    //
    //@+node:swot.20241230135844.9: ** updateEmp
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    public abstract void updateEmp(Emp emp);
    //@+doc
    // ----
    //
    //@-others
}
//@+doc
// ----
//@-leo
