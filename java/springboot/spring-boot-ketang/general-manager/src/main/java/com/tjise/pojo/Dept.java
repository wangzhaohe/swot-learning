//@+leo-ver=5-thin
//@+node:swot.20250916094153.1: * @file src/main/java/com/tjise/pojo/Dept.java
//@@language java
//@+doc
// .src/main/java/com/tjise/pojo/Dept.java
// [source,java,linenums]
// ----
//@@c
package com.tjise.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
//@+doc
// ----
//@-leo
