//@+leo-ver=5-thin
//@+node:swot.20250921102752.1: * @file service-order/src/main/java/com/tjise/serviceorder/service/OrderService.java
//@@language java
//@+others
//@+node:swot.20250921102844.1: ** @ignore-node import
package com.tjise.serviceorder.service;

import com.tjise.serviceorder.pojo.Order;
import com.tjise.serviceorder.pojo.OrderDetail;
import com.tjise.serviceorder.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
//@+node:swot.20250921103039.1: ** @ignore-node OrderService
/**
 * 订单服务类
 * 提供订单查询功能，并通过调用商品服务获取商品详细信息
 */
@Service
public class OrderService {
    //@+others
    //@+node:swot.20250921103135.1: *3* @ignore-node ORDER_DATA 使用静态 Map 模拟数据库存储订单数据
    private static final Map<String, Order> ORDER_DATA = new HashMap<String, Order>();
    // 初始化订单数据
    static {
        // 模拟数据库，构造测试数据
        Order order = new Order();
        order.setOrderId("201810300001");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());
        order.setUserId(1L);
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

        // 创建第一个商品详情（仅保存商品ID，需要调用商品微服务获取详细信息）
        Item item = new Item();
        item.setId(1L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));

        // 创建第二个商品详情
        item = new Item();
        item.setId(2L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));

        order.setOrderDetails(orderDetails);

        ORDER_DATA.put(order.getOrderId(), order);
    }
    //@+node:swot.20250921103403.1: *3* Order queryOrderById
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    // 注入商品服务，用于查询商品详细信息
    @Autowired
    private ItemService itemService;
    /**
     * 根据订单ID查询订单数据
     * @param orderId 订单ID
     * @return Order 订单信息，包含完整的商品详情
     */
    public Order queryOrderById(String orderId) throws IOException {
        // 从模拟数据库中查询订单
        Order order = ORDER_DATA.get(orderId);
        if (null == order) {
            return null;
        }
        // 获取订单详情列表
        List<OrderDetail> orderDetails = order.getOrderDetails();
        // 遍历订单详情，通过商品微服务查询商品详细数据
        for (OrderDetail orderDetail : orderDetails) {
            // 通过商品微服务查询商品详细数据
            // Item item = itemService.queryItemById(orderDetail.getItem().getId());  // <1>
            Item item = itemService.queryItemByIdWithOkHttpClient(orderDetail.getItem().getId());  // <2>
            // Item item = itemService.queryItemByIdWithWebClient(orderDetail.getItem().getId());  // <3>
            if (null == item) {
                continue;
            }
            // 将查询到的商品详细信息设置到订单详情中
            orderDetail.setItem(item);
        }
        return order;
    }
    //@@language asciidoc
    //@+doc
    // ----
    // <1> 使用 RestTemplate
    // <2> 使用 OkHttpClient
    // <3> 使用 WebClient
    //@-others
}
//@-others
//@-leo
