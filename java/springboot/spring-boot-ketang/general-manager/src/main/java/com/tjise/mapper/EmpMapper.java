//@+leo-ver=5-thin
//@+node:swot.20250922105319.1: * @file src/main/java/com/tjise/mapper/EmpMapper.java
//@@language java
//@+others
//@+node:swot.20250922105507.1: ** @ignore-node import
package com.tjise.mapper;

import com.tjise.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
//@+node:swot.20250922105523.1: ** @ignore-node interface EmpMapper
@Mapper
public interface EmpMapper {
    //@+others
    //@+node:swot.20250922105319.2: *3* @ignore-node selectCount & selectpage
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
    //@+node:swot.20241104135928.1: *3* list -> 要使用 PageHelper select 所有记录即可
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    // 使用 PageHelper 后查询语句更简单
    @Select("select * from emp")
    public abstract List<Emp> list();
    //@+doc
    // ----
    //@-others
}
//@-others
//@-leo
