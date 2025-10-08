//@+leo-ver=5-thin
//@+node:swot.20251008194128.1: * @file service-order/src/main/java/com/tjise/serviceorder/service/ElegantRetryService.java
//@@language java
//@+others
//@+node:swot.20251008194235.1: ** @ignore-node import
package com.tjise.serviceorder.service;

import com.tjise.serviceorder.client.ItemFeignClient;
import com.tjise.serviceorder.pojo.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

//@+node:swot.20251008194250.1: ** class ElegantRetryService
//@+doc
// [source,java]
// ----
//@@c
//@@language java
@Slf4j
@Service
public class ElegantRetryService {

    @Autowired
    private ItemFeignClient itemFeignClient;
    
    /**
     * 优雅的重试实现 - 使用Spring Retry注解
     * 策略：重试5次 -> 如果都失败 -> 返回降级结果
     */
    @Retryable(
        value = {RuntimeException.class},
        maxAttempts = 5,
        backoff = @Backoff(delay = 500)
    )
    public Item getItemWithElegantRetry(String itemId) {
        Long id = Long.parseLong(itemId);
        log.info("尝试调用商品服务，商品ID: {}", id);
        return itemFeignClient.queryItemById(id);
    }
    
    /**
     * 降级方法 - 当重试5次都失败时调用
     */
    @Recover
    public Item fallbackItem(RuntimeException e, String itemId) {
        Long id = Long.parseLong(itemId);
        log.warn("重试5次后仍然失败，返回降级结果，异常: {}", e.getMessage());
        return new Item(id, "降级商品", null, "服务暂时不可用", 0L);
    }
}
//@+doc
// ----
//
//@-others
//@-leo
