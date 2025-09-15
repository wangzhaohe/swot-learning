//@+leo-ver=5-thin
//@+node:swot.20241015171320.1: * @file spring-boot-ketang/mybatis_quickstart-crud/src/main/java/com/tjise/pojo/Emp.java
//@@language java
//@+doc
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
