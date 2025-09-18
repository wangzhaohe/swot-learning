//@+leo-ver=5-thin
//@+node:swot.20250916094406.1: * @file src/main/java/com/tjise/pojo/Emp.java
//@@language java
//@+doc
// .src/main/java/com/tjise/pojo/emp.java
// [source,java,linenums]
// ----
//@@c
package com.tjise.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Short gender;
    private String image;
    private Short job;
    private LocalDate entrydate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
//@+doc
// ----
//@-leo
