//@+leo-ver=5-thin
//@+node:swot.20250922084558.1: * @file src/main/java/com/tjise/pojo/PageBean.java
//@@language java
package com.tjise.pojo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    // 总记录数 select count(*) from emp;
    private long total;
    // 当前页数据列表 select * from emp limit ?,?;
    private List rows;
}
//@-leo
