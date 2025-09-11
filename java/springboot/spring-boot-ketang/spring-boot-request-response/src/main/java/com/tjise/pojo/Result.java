//@+leo-ver=5-thin
//@+node:swot.20241016134143.15: * @file src/main/java/com/tjise/pojo/Result.java
//@@language java
//@+doc
// .Result.java 新增静态方法 success
// [source,java,linenums,highlight=8..11]
// ----
//@@c
package com.tjise.pojo;

public class Result {
    private Integer code;   //  响应码: 1 成功，0 失败
    private String msg;     // 响应码描述字符串
    private Object data;    // 返回数据

    // 新增静态方法 success
    public static Result success(Object data) {  // <1>
        return new Result(1, "success", data);
    }

    // @others 表示省略显示构造方法和 getter & setter
    //@+others
    //@+node:swot.20241016134143.16: ** @ignore-node 省略构造方法和 getter & setter
    public Result() {}

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
    //@-others
}
//@+doc
// ----
//
// <1> Object 是所有类型的父类，因为我们不知道 data 会是什么样的数据，所以此处用 Object 来声明形参变量 data。
//@-leo
