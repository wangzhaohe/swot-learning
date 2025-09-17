//@+leo-ver=5-thin
//@+node:swot.20250914214630.1: * @file service-order/src/main/java/com/tjise/serviceorder/service/ItemService.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.serviceorder.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjise.serviceorder.pojo.Item;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
public class ItemService {
    // 下面这两种方式是等价的，看自己的使用方式而定
    // 方式一：字段注入（需要 @Autowired）
    // @Autowired
    // private WebClient webClient;

    // 方式二 单个构造函数注入
    private final WebClient webClient;
    public ItemService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Item queryItemById(Long id) {
        return webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .bodyToMono(Item.class)
            .block(); // 同步调用，如需要异步可去掉block()
    }
}
//@+doc
// ----
//@-leo
