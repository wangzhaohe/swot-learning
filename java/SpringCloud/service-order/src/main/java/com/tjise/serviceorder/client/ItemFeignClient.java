//@+leo-ver=5-thin
//@+node:swot.20251005124609.18: * @file service-order/src/main/java/com/tjise/serviceorder/client/ItemFeignClient.java
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

@FeignClient(name = "app-item")
public interface ItemFeignClient {
    @GetMapping("/item/{id}")
    Item queryItemById(@PathVariable("id") Long id);
}
//@+doc
// ----
//@-leo
