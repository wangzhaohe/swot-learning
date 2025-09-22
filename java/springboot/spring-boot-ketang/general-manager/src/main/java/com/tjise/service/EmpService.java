//@+leo-ver=5-thin
//@+node:swot.20250922161257.1: * @file src/main/java/com/tjise/service/EmpService.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.service;

import com.tjise.pojo.PageBean;
import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    //@+others
    //@+node:swot.20250922161552.1: ** @ignore-node selectPage
    public abstract PageBean selectPage(
            Integer page,
            Integer pageSize,
            String name,
            Short gender,
            LocalDate begin,
            LocalDate end);
    //@+node:swot.20241104152550.1: ** deleteEmpByIds
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    void deleteEmpByIds(List<Integer> ids);
    //@+doc
    // ----
    //
    //@-others
}
//@+doc
// ----
//@-leo
