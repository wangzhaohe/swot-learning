//@+leo-ver=5-thin
//@+node:swot.20250922161310.1: * @file src/main/java/com/tjise/mapper/EmpMapper.java
//@@language java
//@+others
//@+node:swot.20250922161310.2: ** @ignore-node import
package com.tjise.mapper;

import com.tjise.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
//@+node:swot.20250922161310.3: ** @ignore-node interface EmpMapper
@Mapper
public interface EmpMapper {
    //@+others
    //@+node:swot.20250922161310.4: *3* @ignore-node selectCount & selectpage
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    // 查询记录总数
    @Select("select count(*) from emp")
    public abstract Long selectCount();

    // 查询分页数据
    @Select("select * from emp LIMIT #{offset}, #{pageSize}")
    public abstract List<Emp> selectPage(int offset, Integer pageSize);
    //@+doc
    // ----
    //@+node:swot.20250922161310.5: *3* @ignore-node list 多条件查询传递多个参数
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    // 使用 PageHelper 并配合条件查询，使用 xml 动态 sql 来实现
    public abstract List<Emp> list(
            String name,
            Short gender,
            LocalDate begin,
            LocalDate end
    );
    //@+doc
    // ----
    //@+node:swot.20241104152608.1: *3* deleteEmpByIds
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    public abstract void deleteEmpByIds(List<Integer> ids);
    //@+doc
    // ----
    //
    //@-others
}
//@-others
//@-leo
