//@+leo-ver=5-thin
//@+node:swot.20251008212101.1: * @file service-item/src/main/java/com/tjise/serviceitem/controller/ItemController.java
//@@language java
//@+others
//@+node:swot.20251008212101.2: ** @ignore-node import
package com.tjise.serviceitem.controller;

import com.tjise.serviceitem.pojo.Item;
import com.tjise.serviceitem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.MimeHeaders;
import java.util.logging.Logger;

//@+node:swot.20251008212101.3: ** public class ItemController
//@+doc
// [source,java]
// ----
//@@c
//@@language java
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    @Value("${server.port}")
    private int serverPort;

    private static final Logger logger = Logger.getLogger(
                         ItemController.class.getName());
    /**
     * 对外提供接口服务，查询商品信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "item/{id}")
    public Item queryItemById(@PathVariable("id") Long id,
                              HttpServletRequest request) {
        String header = request.getHeader("X-Token");
        System.out.println("header token = " + header);
        // 增加了日志打印功能，方便查看是哪个 service-item 提供的服务。
//        logger.info("Handling request on port: " + serverPort + " for item ID: " + id);
        System.out.println("Processing request on port: " + serverPort + " for item ID: " + id);
        
        // -- New Added Begin --- 测试用例，当 ID 为 -1 时抛出异常
        if (id == -1) {
            System.out.println("=== 触发异常测试，ID 为 -1 ===");
            throw new RuntimeException("服务内部错误");
        }
        // -- New Added End -- 
        return this.itemService.queryItemById(id);  // 正常返回
    }
}
//@+doc
// ----
//
//@-others
//@-leo
