//@+leo-ver=5-thin
//@+node:swot.20250916094843.1: * @file src/main/java/com/tjise/pojo/Result.java
//@@language java
//@+doc
// .src/main/java/com/tjise/pojo/Result.java
// [source,java,linenums]
// ----
//@@c
package com.tjise.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;  // 响应码, 1 代码成功; 0 代表失败
    private String  msg;   // 响应码描述字符串
    private Object  data;  // 返回的数据
    
    // 增删改 成功响应 没有返回数据 null
    public static Result success() {
        return new Result(1, "success", null);
    }

    // 查询 成功响应 有返回数据 data
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    // 失败响应
    public static Result error(String msg) {
        return new Result(0, msg, null);
    }
}
//@+doc
// ----
//@-leo
