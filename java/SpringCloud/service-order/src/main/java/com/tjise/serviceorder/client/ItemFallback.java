//@+leo-ver=5-thin
//@+node:swot.20251005174728.1: * @file service-order/src/main/java/com/tjise/serviceorder/client/ItemFallback.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.serviceorder.client;

import com.tjise.serviceorder.pojo.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemFallback implements ItemFeignClient {
    @Override
    public Item queryItemById(Long id) {
        System.out.println("=== ItemFallback.queryItemById 兜底回调被调用 ===");
        return new Item(id, "Feign 降级商品", null, null, null);
    }
}
//@+doc
// ----
//
//@-leo
