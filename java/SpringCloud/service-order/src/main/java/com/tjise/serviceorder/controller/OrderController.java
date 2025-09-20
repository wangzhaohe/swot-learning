//@+leo-ver=5-thin
//@+node:swot.20250920110103.1: * @file service-order/src/main/java/com/tjise/serviceorder/controller/OrderController.java
//@@language java
//@+others
//@+node:swot.20250920111410.1: ** docs
//@+doc
// // httpie 测试获取订单详情
// // http :8082/order/201810300001
//
// httpie 测试已经注册的微服务:
// http :8082/services
//
// .返回都注册了哪些微服务
// ....
// HTTP/1.1 200
// Connection: keep-alive
// Content-Type: application/json
// Date: Sat, 20 Sep 2025 03:05:52 GMT
// Keep-Alive: timeout=60
// Transfer-Encoding: chunked
//
// [
//     "app-item",
//     "app-order"
// ]
// ....
//
// 说明上面的两个微服务在 Eureka 中已经注册成功了。
//@+node:swot.20250920110131.1: ** @ignore-node import
package com.tjise.serviceorder.controller;

import com.tjise.serviceorder.pojo.Order;
import com.tjise.serviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
//@+node:swot.20250920110227.1: ** @ignore-node class OrderController
/**
 * 订单控制器
 * 处理订单相关的HTTP请求
 */
@RestController
public class OrderController {  
    // 注入订单服务
    @Autowired
    private OrderService orderService;
    //@+others
    //@+node:swot.20250920111059.1: *3* @ignore-node queryOrderById
    /**
     * 根据订单ID查询订单信息
     * 
     * @param orderId 订单ID
     * @return Order 订单信息
     */
    @GetMapping(value = "order/{orderId}")
    public Order queryOrderById(@PathVariable("orderId") String orderId) throws IOException {
        return orderService.queryOrderById(orderId);
    }
    //@+node:swot.20250920111147.1: *3* get services in Eureka 看看都注册了哪些微服务
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Autowired
    private org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public List<String> getServices() {
        return discoveryClient.getServices();
    }
    //@+doc
    // ----
    //@-others
}
//@-others
//@-leo
