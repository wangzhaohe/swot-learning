//@+leo-ver=5-thin
//@+node:swot.20250915162444.1: * @file src/test/java/com/tjise/MybatisQuickstartCrudApplicationTests.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise;

import com.tjise.mapper.EmpMapper;
import com.tjise.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;


@SpringBootTest
class MybatisQuickstartCrudApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    // 省略其他代码显示
    //@+others
    //@+node:swot.20250915162516.1: ** @ignore-node delete test
    @Test
    void deleteEmpByIdTest() {
        empMapper.deleteEmpById(17);
    }

    @Test
    void deleteFromTableByIdTest() {
        empMapper.deleteFromTableById("emp");
    }
    //@+node:swot.20250915162835.1: ** @ignore-node insertEmpTest
    @Test
    public void insertEmpTest(){
        // 创建实体类对象
        Emp emp = new Emp();
        emp.setUsername("zhouyuan");
        emp.setName("周元");
        emp.setGender((short) 2);
        emp.setImage("2.png");
        emp.setJob((short) 2);      // 讲师
        emp.setEntrydate(LocalDate.of(2003,2,10));
        emp.setDeptId(2);   // 先随便给个数值吧
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insertEmp(emp);
        System.out.println("emp.id: " + emp.getId());
    }
    //@+node:swot.20250915162847.1: ** @ignore-node updateEmpTest
    @Test
    public void updateEmpTest(){
        // 创建实体类对象
        Emp emp = new Emp();
        emp.setId(13);
        emp.setUsername("fanghan");
        emp.setName("方寒");
        emp.setGender((short) 2);
        emp.setImage("2.png");
        emp.setJob((short) 2);      // 讲师
        emp.setEntrydate(LocalDate.of(2003,2,10));
        emp.setDeptId(2);   // 先随便给个数值吧
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.updateEmp(emp);
    }
    //@-others

    @Test
    public void selectEmpByIdTest() {
        Emp emp = empMapper.selectEmpById(13);
        System.out.println(emp);  // 有的字段封装失败
    }

}
//@+doc
// ----
//
// .控制台打印更新记录成功
// ....
// ==>  Preparing: update emp set username=?, name=?, gender=?, image=?, job=?, entrydate=?, dept_id=?, update_time=? where id=?
// ==> Parameters: fanghan(String), 方寒(String), 2(Short), 2.png(String), 2(Short), 2003-02-10(LocalDate), 2(Integer), 2024-10-19T20:05:23.088(LocalDateTime), 13(Integer)
// <==    Updates: 1
// ....
//@-leo
