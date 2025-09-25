//@+leo-ver=5-thin
//@+node:swot.20241031090121.4: * @file src/main/java/com/tjise/service/EmpService.java
//@@language java
//@+others
//@+node:swot.20250816112818.1: ** @ignore-node import
package com.tjise.service;

import com.tjise.pojo.Emp;
import com.tjise.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

//@+node:swot.20250816112852.1: ** @ignore-node public interface EmpService
public interface EmpService {
    //@+others
    //@+node:swot.20250817004231.1: *3* @ignore-tree
    //@+others
    //@+node:swot.20250816113055.1: *4* selectPage
    public abstract PageBean selectPage(
        Integer page,
        Integer pageSize,
        String name,
        Short gender,
        LocalDate begin,
        LocalDate end
    );
    //@+node:swot.20250816113051.1: *4* deleteEmpByIds
    void deleteEmpByIds(List<Integer> ids);

    //@+node:swot.20250816113047.1: *4* insertEmp
    void insertEmp(Emp emp);    

    //@+node:swot.20250816113044.1: *4* getEmpById
    public abstract Emp getEmpById(Integer id);

    //@+node:swot.20250816113040.1: *4* updateEmp
    public abstract void updateEmp(Emp emp);

    //@-others
    //@+node:swot.20250817055301.1: *3* login -> New Add
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    Emp login(Emp emp);
    //@+doc
    // ----
    //
    //@-others
}

//@-others
//@-leo
