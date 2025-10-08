//@+leo-ver=5-thin
//@+node:swot.20251008194645.1: * @file service-order/src/main/java/com/tjise/serviceorder/controller/ElegantRetryController.java
//@@language java
//@+others
//@+node:swot.20251008194731.1: ** @ignore-node import
package com.tjise.serviceorder.controller;

import com.tjise.serviceorder.pojo.Item;
import com.tjise.serviceorder.service.ElegantRetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@+node:swot.20251008194756.1: ** class ElegantRetryController
//@+doc
// [source,java]
// ----
//@@c
//@@language java
@Slf4j
@RestController
@RequestMapping("/elegant")
public class ElegantRetryController {

    @Autowired
    private ElegantRetryService elegantRetryService;

    /**
     * 测试优雅的重试机制
     * 策略：重试5次 -> 如果都失败 -> 返回降级结果
     */
    @GetMapping("/item/{id}")
    public Item getItemWithElegantRetry(@PathVariable String id) {
        log.info("开始调用优雅重试服务，商品ID: {}", id);
        Item result = elegantRetryService.getItemWithElegantRetry(id);
        log.info("最终结果: {}", result);
        return result;
    }
}
//@+doc
// ----
//
//@-others
//@-leo
