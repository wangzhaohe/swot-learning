//@+leo-ver=5-thin
//@+node:swot.20250824075940.1: * @file src/main/java/com/tjise/service/EmpService.java
//@@language java
//@+others
//@+node:swot.20250824075940.2: ** @ignore-node import
package com.tjise.service;

import com.tjise.pojo.Emp;
import com.tjise.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

//@+node:swot.20250824075940.3: ** @ignore-node public interface EmpService
public interface EmpService {
    //@+others
    //@+node:swot.20250824075940.4: *3* @ignore-tree
    //@+others
    //@+node:swot.20250824075940.5: *4* PageBean
    PageBean selectPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    //@+node:swot.20250824075940.6: *4* deleteEmpByIds
    void deleteEmpByIds(List<Integer> ids);

    //@+node:swot.20250824075940.7: *4* insertEmp
    void insertEmp(Emp emp);    

    //@+node:swot.20250824075940.8: *4* getEmpById
    public abstract Emp getEmpById(Integer id);

    //@+node:swot.20250824075940.9: *4* updateEmp
    public abstract void updateEmp(Emp emp);

    //@+node:swot.20250824075940.10: *4* login
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
    //@+node:swot.20250824080142.1: *3* deleteEmpByDeptId 根据部门 id 删除员工 -> New
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    int deleteEmpByDeptId(Integer deptId);
    //@+doc
    // ----
    //
    // 因为是要删除员工，所以很合理地应该将删除员工的功能放在 EmpService 中。
    //
    //@-others
}

//@-others
//@-leo
