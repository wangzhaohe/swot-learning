//@+leo-ver=5-thin
//@+node:swot.20250919162334.1: * @file src/main/java/com/tjise/mapper/DeptMapper.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.mapper;

import com.tjise.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper  // 1. 让 mybatis 识别 2. 将该接口的实现类对象放入 IOC 容器中
public interface DeptMapper {
    @Select("select * from dept")
    public abstract List<Dept> selectAllDept();

    // 更改返回值为整形 int
    @Delete("delete from dept where id=#{id}")
    public abstract int deleteDeptById(Integer id);

    @Insert("INSERT INTO dept (name, create_time, update_time) " +
            "VALUES (#{name}, #{createTime}, #{updateTime})")
    public abstract void insertDept(Dept dept);

    @Select("select * from dept where id = #{id}")
    public abstract Dept getDeptById(Integer id);

    // 新增更新单个部门
    @Update("update dept set name = #{name}, update_time=#{updateTime} where id=#{id}")
    public abstract void updateDept(Dept dept);
}
//@+doc
// ----
//@-leo
