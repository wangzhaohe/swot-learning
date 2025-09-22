//@+leo-ver=5-thin
//@+node:swot.20250922092140.1: * @file src/main/java/com/tjise/service/EmpService.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.service;

import com.tjise.pojo.PageBean;

public interface EmpService {
    PageBean selectPage(Integer page, Integer pageSize);
}
//@+doc
// ----
//@-leo
