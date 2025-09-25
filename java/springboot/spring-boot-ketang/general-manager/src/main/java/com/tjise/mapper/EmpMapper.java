//@+leo-ver=5-thin
//@+node:swot.20250816115018.1: * @file src/main/java/com/tjise/mapper/EmpMapper.java
//@@language java
//@+others
//@+node:swot.20250816115233.1: ** @ignore-node import
//@@language java
//@+doc
// [source,java]
// ----
//@@c
package com.tjise.mapper;

import com.tjise.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
//@+doc
// ----
//
//@+node:swot.20250816115018.2: ** EmpMapper
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
@Mapper
public interface EmpMapper {
    //@+others
    //@+node:swot.20250907110053.1: *3* @ignore-tree 
    //@+others
    //@+node:swot.20250816120826.1: *4* list
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    public abstract List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);
    //@+doc
    // ----
    //@+node:swot.20250816120635.1: *4* deleteEmpByIds
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    public abstract void deleteEmpByIds(List<Integer> ids);
    //@+doc
    // ----
    //
    //
    //@+node:swot.20250816120621.1: *4* insertEmp
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    public abstract void insertEmp(Emp emp);
    //@+doc
    // ----
    //
    //@+node:swot.20250816120540.1: *4* getEmpById
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
    //@+node:swot.20250816120527.1: *4* updateEmp
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    public void updateEmp(Emp emp);
    //@+doc
    // ----
    //
    //
    //@-others
    //@+node:swot.20250816115430.1: *3* login -> New Add
    //@@language java
    //@+doc
    // .同时查询用户名和密码，返回结果。
    // [source,java]
    // ----
    //@@c
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp login(Emp emp);
    //@+doc
    // ----
    //
    //@-others
    /*
    @Select("select count(*) from emp")
    Long selectCount();

    @Select("select * from emp LIMIT #{offset}, #{pageSize}")
    List<Emp> selectPage(int offset, Integer pageSize);
    */
}
//@+doc
// ----
//@-others
//@-leo
