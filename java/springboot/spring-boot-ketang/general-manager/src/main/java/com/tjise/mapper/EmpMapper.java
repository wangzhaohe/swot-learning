//@+leo-ver=5-thin
//@+node:swot.20250824085634.1: * @file src/main/java/com/tjise/mapper/EmpMapper.java
//@@language java
//@+others
//@+node:swot.20250824085634.2: ** @ignore-node import
//@@language java
//@+doc
// [source,java]
// ----
//@@c
package com.tjise.mapper;

import com.tjise.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
//@+doc
// ----
//
//@+node:swot.20250824085634.3: ** @ignore-node EmpMapper
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
@Mapper
public interface EmpMapper {
    //@+others
    //@+node:swot.20250824085914.1: *3* @ignore-tree
    //@+node:swot.20250824085634.4: *4* list
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    public abstract List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);
    //@+doc
    // ----
    //@+node:swot.20250824085634.5: *4* deleteEmpByIds
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
    //@+node:swot.20250824085634.6: *4* insertEmp
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
    //@+node:swot.20250824085634.7: *4* getEmpById
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
    //@+node:swot.20250824085634.8: *4* updateEmp
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
    //@+node:swot.20250824085634.9: *4* login
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp login(Emp emp);
    //@+doc
    // ----
    //@+node:swot.20250824090045.1: *3* deleteEmpByDeptId 根据部门 id 删除员工 -> 新增
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Delete("delete from emp where dept_id=#{deptId}")
    int deleteEmpByDeptId(Integer deptId);
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
