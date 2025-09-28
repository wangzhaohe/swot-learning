//@+leo-ver=5-thin
//@+node:swot.20250912113856.1: * @file service-item/src/main/java/com/tjise/serviceitem/controller/ItemController.java
//@@language java
package com.tjise.serviceitem.controller;

import com.tjise.serviceitem.pojo.Item;
import com.tjise.serviceitem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    @Value("${server.port}")
    private int serverPort;

    private static final Logger logger = Logger.getLogger(ItemController.class.getName());

    /**
     * 对外提供接口服务，查询商品信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "item/{id}")
    public Item queryItemById(@PathVariable("id") Long id) {
        logger.info("Handling request on port: " + serverPort + " for item ID: " + id);
        System.out.println("Processing request on port: " + serverPort + " for item ID: " + id);
        return this.itemService.queryItemById(id);
    }

}
//@-leo
