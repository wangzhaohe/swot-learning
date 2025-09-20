//@+leo-ver=5-thin
//@+node:swot.20250913164255.1: * @file service-order/src/main/java/com/tjise/serviceorder/controller/OrderController.java
//@@language java
package com.tjise.serviceorder.controller;

import com.netflix.discovery.converters.Auto;
import com.tjise.serviceorder.pojo.Order;
import com.tjise.serviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 订单控制器
 * 处理订单相关的HTTP请求
 */
@RestController
public class OrderController {
    
    // 注入订单服务
    @Autowired
    private OrderService orderService;

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

    @Autowired
    private org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public List<String> getServices() {
        return discoveryClient.getServices();
    }
}
//@-leo
