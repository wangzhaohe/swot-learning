//@+leo-ver=5-thin
//@+node:swot.20251014134428.1: * @file service-order/src/main/java/com/tjise/serviceorder/controller/OrderController.java
//@@language java
//@+others
//@+node:swot.20251014134428.2: ** @ignore-node import
package com.tjise.serviceorder.controller;

import com.tjise.serviceorder.pojo.Order;
import com.tjise.serviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//@+node:swot.20251014134428.3: ** class OrderController -- New Added -- @RequestMapping("/api/order")
//@+doc
// [source,java]
// ----
//@@c
//@@language java
/**
 * 订单控制器
 * 处理订单相关的HTTP请求
 */
// @RequestMapping("/api")  // -- 不要了
@RefreshScope
@RestController
public class OrderController {

    // 注入订单服务
    @Autowired
    private OrderService orderService;

    // 从配置中心获取的配置
    @Value("${app.name:未配置}")
    private String appName;

    @Value("${app.version:未配置}")
    private String appVersion;

    @Value("${demo.message:未配置}")
    private String demoMessage;

    @Value("${server.port:未配置}")
    private String serverPort;

    /**
     * 根据订单ID查询订单信息
     *
     * @param orderId 订单ID
     * @return Order 订单信息
     */
    @GetMapping(value = "/order/{orderId}")
    public Order queryOrderById(@PathVariable("orderId") String orderId) throws IOException {
        return orderService.queryOrderById(orderId);
    }

    /**
     * 验证配置中心配置
     *
     * @return 配置信息
     */
    @GetMapping("/config")
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("appName", appName);
        config.put("appVersion", appVersion);
        config.put("demoMessage", demoMessage);
        config.put("serverPort", serverPort);
        config.put("configSource", "Spring Cloud Config Server");
        config.put("profile", "dev");
        return config;
    }
}
//@@language asciidoc
//@+doc
// ----
//@-others
//@+doc
//@-leo
