//@+leo-ver=5-thin
//@+node:swot.20250826210125.1: * @file src/main/java/com/tjise/pojo/OperateLog.java
//@+doc
// .定义数据库操作日志表 operate_log 对应的实体类
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.pojo;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {
    private Integer id; //ID
    private Integer operateUser; //操作人
    private LocalDateTime operateTime; //操作时间
    private String className; //操作类名
    private String methodName; //操作方法名
    private String methodParams; //操作方法参数
    private String returnValue; //操作方法返回值
    private Long costTime; //操作耗时
}
//@+doc
// ----
//@-leo
