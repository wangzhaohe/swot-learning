//@+leo-ver=5-thin
//@+node:swot.20250922092154.1: * @file src/main/java/com/tjise/mapper/EmpMapper.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.mapper;

import com.tjise.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
    //@+others
    //@+node:swot.20241104134755.1: ** selectCount & selectpage
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
    //@-others
}
//@+doc
// ----
//@-leo
