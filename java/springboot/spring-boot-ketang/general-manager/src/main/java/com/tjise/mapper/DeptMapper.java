//@+leo-ver=5-thin
//@+node:swot.20241031174419.1: * @file src/main/java/com/tjise/mapper/DeptMapper.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.mapper;

import com.tjise.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;


@Mapper  // 1. 让 mybatis 识别 2. 将该接口的实现类对象放入 IOC 容器中
public interface DeptMapper {
    @Select("select * from dept")
    public abstract List<Dept> selectAllDept();
}
//@+doc
// ----
//@-leo
