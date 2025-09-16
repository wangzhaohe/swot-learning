//@+leo-ver=5-thin
//@+node:swot.20241022173912.1: * @file src/main/java/com/tjise/mapper/EmpMapper.java
//@@language java
//@+doc
// .使用 xml 后的 EmpMapper
// [source,java,linenums]
// ----
//@@c
package com.tjise.mapper;

import com.tjise.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    // 省略其他代码显示
    //@+others
    //@+node:swot.20241022173912.2: ** @ignore-node others
    /* 删除记录接口方法如下 */

    // @Delete("delete from emp where id = #{id}")
    @Delete("delete from emp where id = ${id}")
    public abstract void deleteEmpById(Integer id);

    // 两种情况测试代码
    @Delete("delete from #{tableName} where id = 17")  // <1>
    //  @Delete("delete from ${tableName} where id = 17")  // <2>
    public abstract void deleteFromTableById(String tableName);


    // @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            //  values ('wanglin', '王林', 1, '1.png', 2, '2003-02-10', 2, now(), now())")
    /* 方法的参数是一个实体类对象，取实体类对象中的属性为: #{成员变量名} */
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            " values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    public abstract void insertEmp(Emp emp);


    // 注意 #{这儿是类的成员变量名}
    @Update("update emp set username=#{username}, name=#{name}, gender=#{gender}, image=#{image}, job=#{job}, entrydate=#{entrydate}, dept_id=#{deptId}, update_time=#{updateTime} where id=#{id}")
    public abstract void updateEmp(Emp emp);


    @Select("select * from emp where id = #{id}")
    public abstract Emp selectEmpById(Integer id);


    // 使用 sql 中的函数 concat()
    @Select("select * from emp where name like concat('%', #{name}, '%') " +
                                "and gender = #{gender} " +
                                "and entrydate between #{begin} and #{end} " +
                                "order by update_time desc")
    public List<Emp> listEmp(
        String name,
        Short gender,
        LocalDate begin,
        LocalDate end
    );

    public List<Emp> listEmpXml(
        String name,
        Short gender,
        LocalDate begin,
        LocalDate end
    );

    public List<Emp> listEmpXmlIf(
        String name,
        Short gender,
        LocalDate begin,
        LocalDate end
    );

    public abstract void updateEmpXml(Emp emp);
    //@-others

    public abstract void deleteEmpByIdsXml(List<Integer> ids);
}
//@+doc
// ----
//@-leo
