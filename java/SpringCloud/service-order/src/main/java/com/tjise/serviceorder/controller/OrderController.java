//@+leo-ver=5-thin
//@+node:swot.20251012202932.1: * @file service-order/src/main/java/com/tjise/serviceorder/controller/OrderController.java
//@@language java
//@+others
//@+node:swot.20251012203322.1: ** @ignore-node import
package com.tjise.serviceorder.controller;

import com.tjise.serviceorder.pojo.Order;
import com.tjise.serviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
//@+node:swot.20251012203351.1: ** class OrderController -- New Added -- @RequestMapping("/api/order")
//@+doc
// [source,java]
// ----
//@@c
//@@language java
/**
 * 订单控制器
 * 处理订单相关的HTTP请求
 */
@RequestMapping("/api")  // -- New Added --
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
}
//@@language asciidoc
//@+doc
// ----
//
// httpie 测试:
// http :8091/order/201810300001
//@-others
//@+doc
//@-leo
