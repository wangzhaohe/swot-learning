//@+leo-ver=5-thin
//@+node:swot.20251014134438.1: * @file service-order/src/main/java/com/tjise/serviceorder/client/ItemFeignClient.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.serviceorder.client;

import com.tjise.serviceorder.pojo.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 要访问的在 Eureka 中的服务名，并指定实现该接口的降级类名
@FeignClient(name = "app-item", fallback = ItemFallback.class)
public interface ItemFeignClient {
    // @GetMapping("/api/item/{id}")  // 不要了
    @GetMapping("/item/{id}")
    Item queryItemById(@PathVariable("id") Long id);
}
//@+doc
// ----
//@-leo
