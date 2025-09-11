//@+leo-ver=5-thin
//@+node:swot.20241015083350.9: * @file spring-boot-ketang/spring-boot-request-response/src/main/java/com/tjise/controller/RequestController.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.controller;

// import 都是下面例子用到的时候逐个导入的
import com.tjise.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class RequestController {
    // @others 伪代码表示此处还会有很多具体的代码
    //@+others
    //@+node:swot.20241015083350.10: ** 简单请求参数原始方式演示(了解以后不用)
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @RequestMapping("/simpleParamOld")
    public String simpleParamOld(HttpServletRequest request,
                                 HttpServletResponse response)
    {
        String name = request.getParameter("name");
        String age  = request.getParameter("age");
        System.out.println(name + " : " + age);
        return "ok";
    }
    //@+doc
    // ----
    //
    // 使用 httpie 测试：
    //
    // http "http://localhost:8080/simpleParamOld?name=Swot&age=19"
    //
    // NOTE:  只能获取 url 携带的参数。
    //@+node:swot.20241015083350.11: ** 简单请求参数: 前端提交与方法形参一致
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    @RequestMapping("/simpleParam1")
    public String simpleParam1(String name, Integer age) {
        System.out.println(name + " : " + age);
        return "ok";
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // 注意事项:
    //
    // . 前端请求参数名与形参变量名相同，定义形参即可接收数据
    //     * 如前端请求 http://localhost:8080/simpleParam1/?name=王林&age=400
    //     * url 中的 name 对应形参 String name，age  对应形参 Integer age
    //
    // . 参数类型可以自动类型转换，基本类型需要使用包装类类型接收
    //     * age 网上传过来的是 String，现在已经是 Integer 类型了
    //     * Integer 是包装类类型
    //
    // . 如果前端请求参数名与方法形参名称不一致，可以使用 @RequestParam 完成映射
    //     * 参下面 @RequestParam 例子
    //
    // 此方法可获取 get 方法 url 携带的参数（如上面的 url）或者 post 方法 body 使用 x-www-form-urlencoded 形式携带的参数。
    //
    // body 中发送 http://localhost:8080/simpleParam1 选择 x-www-form-urlencoded 类型的数据如下图所示
    //
    // image::img/request_simple_param_post_body1.png[]
    //
    // .使用 httpie 测试
    // [source,console]
    // ----
    // http --form POST localhost:8080/simpleParam1 \
    //   name="王林" \
    //   age="400"
    // ----
    //@+node:swot.20241015083350.12: ** @RequestParam 简单请求参数不一致
    //@@language java
    //@+doc
    // [source,java,linenums]
    // ----
    //@@c
    @RequestMapping("/simpleParam2")
    public String simpleParam2(@RequestParam("username") String name,
                               Integer age) {
        System.out.println(name + " : " + age);
        return "ok";
    }
    //@+doc
    // ----
    //
    // 如果前端请求参数名与方法形参名称不一致，可以使用 @RequestParam 完成映射
    //
    //     * 如前端请求 http://localhost:8080/simpleParam2/?username=韩立&age=2000
    //     * username 的内容会被 name 接收到
    //
    // .使用 httpie 测试
    // [source,console]
    // ----
    // http "http://localhost:8080/simpleParam2/?username=韩立&age=2000"
    // ----
    //@+node:swot.20241015083350.13: ** 简单实体对象参数
    //@@language java
    //@+doc
    // 1. 创建包 pojo，User 实体类放在包 pojo 中，参 pojo/User.java
    //     * POJO: 在 Java 中，POJO 是 Plain Old Java Object 的缩写，意为简单的 Java 对象。它指的是一个普通的没有任何特殊要求或依赖的 Java 类，通常用来作为实体类来封装数据。POJO 类并不继承特定的父类，也不需要实现特定的接口，因此保持了很大的自由度和简单性。
    //
    // 2. 前端传入参数名与 User 属性名相同
    //     * 访问 url: http://localhost:8080/simplePojo/?name=韩立&age=2000
    //     * 服务器打印数据: `User{name='韩立', age=2000}`
    //
    // [source,java,linenums]
    // ----
    //@@c
    @RequestMapping("/simplePojo")
    public String simplePojo(User user) {
        System.out.println(user);
        return "ok";
    }
    //@+doc
    // ----
    //@+node:swot.20241015083350.14: ** 复杂实体对象参数
    //@@language java
    //@+doc
    // . 请求参数名与形参对象属性名相同，按照对象层次结构关系即可接收嵌套 POJO 属性参数。
    //     * popo/Uesr.java 包含三个属性 user, name, address （address 对应 Address.java）
    //     * popo/Address.java 包含两个属性 province, city
    //
    // . 前端传入参数名与 User 属性名相同
    //     * 访问 url: http://localhost:8080/complexPojo/?name=韩立&age=2000&address.province=河北&address.city=张家口
    //     * 服务器打印数据: `User{name='韩立', age=2000, address=Address{province='河北', city='张家口'}}`
    //
    // [source,java,linenums]
    // ----
    //@@c
    @RequestMapping("/complexPojo")
    public String complexPojo(User user) {
        System.out.println(user);
        return "ok";
    }
    //@+doc
    // ----
    //@+node:swot.20241015083350.15: ** 使用数组接收参数（默认）
    //@+doc
    // 数组参数：请求参数为多个且参数的键是相同的，定义数组类型形参即可接收参数。
    //
    // 访问 url: http://localhost:8080/arrayParam?state=绝情&state=安逸&state=岁月
    //
    // ****
    // state 取自 state of mind 翻译为意境，《仙逆》中王林体验的三种化神意境，分别为绝情之境、安逸之境、岁月之境。
    // ****
    //
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    @RequestMapping("/arrayParam")
    public String arrayParam(String[] state) {
        System.out.println(Arrays.toString(state));
        return "ok";
    }
    //@+doc
    // ----
    //@+node:swot.20241015083350.16: ** @RequestParam   使用集合接收参数（需使用 @RequestParam 指定）
    //@+doc
    // 集合参数：请求参数为多个且参数的键是相同的，定义集合类型形参接收并使用 @RequestParam 绑定参数关系。
    //
    // 访问 url: http://localhost:8080/listParam?state=绝情&state=安逸&state=岁月
    //
    // ****
    // state 取自 state of mind 翻译为意境，《仙逆》中王林体验的三种化神意境，分别为绝情之境、安逸之境、岁月之境。
    // ****
    //
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> state) {
        System.out.println(state);  // Spring Boot 默认使用 ArrayList 实现类
        return "ok";
    }
    //@+doc
    // ----
    //@+node:swot.20241015083350.17: ** @DateTimeFormat 日期参数
    //@+doc
    // 日期参数: 前端请求的参数是一个日期，使用日期参数接收且使用 @DateTimeFormat 注解完成日期参数格式转换。
    //
    // 访问url: http://localhost:8080/dateParam?updateTime=2024-10-08 19:19:19
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    @RequestMapping("/dateParam")
    public String dateParam(@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {

        System.out.println(updateTime);
        return "ok";
    }
    //@+doc
    // ----
    //
    // 打印结果是 `2024-10-08T19:19:19` 是 **ISO 8601** 格式的日期时间表示法，具体来说是 **`LocalDateTime`** 类型的标准字符串输出。
    //
    // 解释::
    // - **`2024-10-08`**：表示日期（年-月-日）。
    // - **`T`**：是日期和时间的分隔符，在 ISO 8601 标准中使用。
    // - **`19:19:19`**：表示时间（时:分:秒）。
    //
    // Spring Boot 的 `LocalDateTime` 类型在进行 `toString()` 时会自动按照这种 ISO 8601 格式进行输出。所以，打印出来的结果是 `LocalDateTime` 默认的输出格式。
    //
    // 这个格式不包含时区信息，仅仅表示日期和时间。
    //@+node:swot.20241015083350.18: ** @RequestBody    解析 json 数据
    //@+doc
    // 1. 请求参数是 JSON 数据，使用 POJO 类型接收参数。
    // 2. 需使用 @RequestBody 标识形参
    //
    // .获取 json 数据
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user) {
        System.out.println(user);  // <1>
        return "ok";
    }
    //@+doc
    // ----
    //
    // .Postman 使用 POST 方法访问 url: http://localhost:8080/jsonParam 提交 body 中的 json
    // [source,json]
    // ----
    // {
    //     "name": "王林",
    //     "age": 400,
    //     "address": {
    //         "province": "赵国",
    //         "city": "夔牛镇"
    //     }
    // }
    // ----
    //
    // <1> 后端打印结果为
    // +
    // ....
    // User{name='王林', age=400, address=Address{province='赵国', city='夔牛镇'}}
    // ....
    //
    //
    // .httpie 测试
    // [source,console]
    // ----
    // http -v POST http://localhost:8080/jsonParam \
    //   name="王林" \
    //   age:=400 \
    //   address:='{"province": "赵国", "city": "夔牛镇"}'
    // ----
    //@+node:swot.20241015083350.19: ** @PathVariable   路径参数
    //@+doc
    // 1. 参数在 url 路径中
    // 2. 需使用 {变量名} 标识该路径参数
    // 3. 需要使用 @PathVariable 获取路径参数
    //
    // Postman 访问 url: http://localhost:8080/pathParam/19 (RESTful风格的单条记录处理方式)
    //
    // .获取路径参数
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    @RequestMapping("/pathParam/{id}")
    public String pathParam(@PathVariable Integer id) {
        System.out.println(id);  // 19
        return "ok";
    }
    //@+doc
    // ----
    //
    //
    // .扩展多个路径参数举例
    // ====
    // Postman 访问 url: http://localhost:8080/pathParam/19/李慕婉
    //
    // [source,java,linenums]
    // ----
    //@@c
    //@@language java
    @RequestMapping("/pathParam/{id}/{girl}")
    public String pathParam(@PathVariable Integer id,
                            @PathVariable String girl)
    {
        System.out.println(id);    // 19
        System.out.println(girl);  // 李慕婉
        return "ok";
    }
    //@+doc
    // ----
    // ====
    //@-others
}
//@+doc
// ----
//@-leo
