//@+leo-ver=5-thin
//@+node:swot.20250922113452.5: * @file src/main/java/com/tjise/service/EmpService.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.service;

import com.tjise.pojo.PageBean;

import java.time.LocalDate;

public interface EmpService {
    public abstract PageBean selectPage(
            Integer page,
            Integer pageSize,
            String name,
            Short gender,
            LocalDate begin,
            LocalDate end);
}
//@+doc
// ----
//@-leo
