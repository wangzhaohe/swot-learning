//@+leo-ver=5-thin
//@+node:swot.20241022222115.1: * @file src/test/java/com/tjise/MybatisQuickstartCrudApplicationTests.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise;

import com.tjise.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@SpringBootTest
class MybatisQuickstartCrudApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void deleteEmpByIdsXmlTest() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10, 11, 12);
        empMapper.deleteEmpByIdsXml(list);
    }
}
//@+doc
// ----
//
// .测试通过，控制台打印生成的 sql 语句正确`。
// ....
// ==>  Preparing: DELETE FROM emp WHERE id IN ( ? , ? , ? )
// ==> Parameters: 18(Integer), 19(Integer), 22(Integer)
// <==    Updates: 3
// ....
//@-leo
