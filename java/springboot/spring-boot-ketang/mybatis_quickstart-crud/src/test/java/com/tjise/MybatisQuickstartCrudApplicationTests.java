//@+leo-ver=5-thin
//@+node:swot.20241016164835.1: * @file src/test/java/com/tjise/MybatisQuickstartCrudApplicationTests.java
//@@language java
//@+doc
// 接下来，我们就可以直接在单元测试类中通过 @Autowired 注解 EmpMapper 接口。
// 然后就可以直接调用其 deleteEmpById 方法传递参数进行测试了。
//
// [source,java,linenums]
// ----
//@@c
package com.tjise;

import com.tjise.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MybatisQuickstartCrudApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    void deleteEmpByIdTest() {
        empMapper.deleteEmpById(15);
    }

}
//@+doc
// ----
//
// 测试结果为删除了指定的数据库记录。
//@-leo
