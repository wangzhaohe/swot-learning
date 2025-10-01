//@+leo-ver=5-thin
//@+node:swot.20251001110527.1: * @file service-order/src/main/java/com/tjise/serviceorder/service/OrderService.java
//@@language java
//@+others
//@+node:swot.20251001110527.2: ** @ignore-node import
package com.tjise.serviceorder.service;

import com.tjise.serviceorder.pojo.Order;
import com.tjise.serviceorder.pojo.OrderDetail;
import com.tjise.serviceorder.pojo.Item;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;
//@+node:swot.20251001110527.3: ** class OrderService
/**
 * 订单服务类
 * 提供订单查询功能，并通过调用商品服务获取商品详细信息
 */
@Service
public class OrderService {
    
    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;
    
    //@+others
    //@+node:swot.20251001110527.4: *3* @ignore-tree ORDER_DATA 模拟数据
    // 使用静态Map模拟数据库存储订单数据
    private static final Map<String, Order> ORDER_DATA = new HashMap<String, Order>();
    // 初始化订单数据
    static {
        // 模拟数据库，构造测试数据
        //@+others
        //@+node:swot.20251001110527.5: *4* 第一个订单 order
        Order order = new Order();
        order.setOrderId("201810300001");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());  // 真会偷懒呀
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
        //@+node:swot.20251001110527.6: *4* 第二个订单 order2
        Order order2 = new Order();
        order2.setOrderId("201810300002");
        order2.setCreateDate(new Date());
        order2.setUpdateDate(order.getCreateDate());  // 真会偷懒呀
        order2.setUserId(2L);
        List<OrderDetail> orderDetails2 = new ArrayList<OrderDetail>();

        // 创建第一个商品详情（仅保存商品ID，需要调用商品微服务获取详细信息）
        Item item2 = new Item();
        item2.setId(3L);
        orderDetails2.add(new OrderDetail(order2.getOrderId(), item2));

        // 创建第二个商品详情
        item2 = new Item();
        item2.setId(4L);
        orderDetails2.add(new OrderDetail(order2.getOrderId(), item2));

        order2.setOrderDetails(orderDetails2);

        ORDER_DATA.put(order2.getOrderId(), order2);
        //@-others
    }
    //@+node:swot.20251001115941.1: *3* Item queryItemByIdWithCircuitBreaker
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    // name 对应 application.yml 中的配置
    public Item queryItemByIdWithCircuitBreaker(Long id) {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("OrderService");
        return circuitBreaker.executeSupplier(() -> {
            try {
                return itemService.queryItemByIdWithWebClient(id);
            } catch (Exception e) {
                // 如果发生异常，调用降级方法
                return queryItemByIdFallback(id, e);
            }
        });
    }
    //@+doc
    // ----
    //
    //@+node:swot.20251001083156.1: *3* Item queryItemByIdFallback 断路器降级方法
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    /**
     * 断路器降级方法
     * @param id 商品 ID
     * @param throwable 抛出的异常
     * @return 降级后的默认商品信息
     */
    public Item queryItemByIdFallback(Long id, Throwable throwable) {
        System.out.println("=======CircuitBreaker 降级处理，原因：" + throwable.getMessage());
        return new Item(id, "查询商品信息出错", null, null, null);
    }
    //@+doc
    // ----
    //
    //@+node:swot.20251001110527.7: *3* queryOrderById
    // 注入商品服务，用于查询商品详细信息
    @Autowired
    private ItemService itemService;
    /**
     * 根据订单ID查询订单数据
     * 
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
            Item item = queryItemByIdWithCircuitBreaker(orderDetail.getItem().getId());
            if (null == item) {
                continue;
            }
            // 将查询到的商品详细信息设置到订单详情中
            orderDetail.setItem(item);
        }
        return order;
    }
    //@-others
}
//@-others
//@-leo
