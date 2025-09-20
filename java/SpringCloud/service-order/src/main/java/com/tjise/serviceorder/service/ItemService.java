//@+leo-ver=5-thin
//@+node:swot.20250920121016.1: * @file service-order/src/main/java/com/tjise/serviceorder/service/ItemService.java
//@@language java
package com.tjise.serviceorder.service;

import com.tjise.serviceorder.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 商品服务类
 * 通过 REST 方式调用商品微服务获取商品信息
 */
@Service
public class ItemService {

    // Spring 框架对 RESTful 方式的 http 请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 根据商品 ID 查询商品信息
     * 通过 REST 调用商品微服务获取商品详细数据
     * 
     * @param id 商品ID
     * @return Item 商品信息
     */
    public Item queryItemById(Long id) {
        return restTemplate.getForObject("http://app-item/item/"
                + id, Item.class);
    }
}
//@-leo
