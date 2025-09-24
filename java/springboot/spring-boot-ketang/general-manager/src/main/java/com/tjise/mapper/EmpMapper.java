//@+leo-ver=5-thin
//@+node:swot.20250924160811.1: * @file src/main/java/com/tjise/mapper/EmpMapper.java
//@@language java
//@+others
//@+node:swot.20250924160811.2: ** @ignore-node import
package com.tjise.mapper;

import com.tjise.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
//@+node:swot.20250924160811.3: ** @ignore-node interface EmpMapper
@Mapper
public interface EmpMapper {
    //@+others
    //@+node:swot.20250924160811.4: *3* @ignore-node selectCount & selectpage
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
    //@+node:swot.20250924160811.5: *3* @ignore-node list 多条件查询传递多个参数
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
    //@+node:swot.20250924160811.6: *3* @ignore-node deleteEmpByIds
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    public abstract void deleteEmpByIds(List<Integer> ids);
    //@+doc
    // ----
    //
    //@+node:swot.20250924160811.7: *3* @ignore-node insertEmp
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    public abstract void insertEmp(Emp emp);
    //@+doc
    // ----
    //
    //@+node:swot.20250924160811.8: *3* @ignore-node getEmpById
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    @Select("select * from emp where id = #{id}")
    public abstract Emp getEmpById(Integer id);
    //@+doc
    // ----
    //
    //@+node:swot.20241230135844.11: *3* updateEmp
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    // 因为要判断值是否为空，所以要用到动态 SQL 了
    public void updateEmp(Emp emp);
    //@+doc
    // ----
    //
    //@-others
}
//@-others
//@-leo
