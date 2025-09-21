//@+leo-ver=5-thin
//@+node:swot.20250920121016.1: * @file service-order/src/main/java/com/tjise/serviceorder/service/ItemService.java
//@@language java
//@+others
//@+node:swot.20250921083535.1: ** import
package com.tjise.serviceorder.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjise.serviceorder.pojo.Item;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
//@+node:swot.20250921083629.1: ** class ItemService
//@+doc
// [source,java]
// ----
//@@c
//@@language java
// 根据商品 ID 查询商品信息：通过 REST 调用商品微服务获取商品详细数据
@Service
public class ItemService {  // 商品服务类
    @Autowired
    private RestTemplate restTemplate;

    private final WebClient webClient;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;  // 可支持 json 序列化

    // 单个构造方法注入
    public ItemService(
            WebClient webClient,
            @Autowired(required = false) OkHttpClient okHttpClient,
            @Autowired(required = false) ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }
    // 包含其他代码
    //@+others
    //@+node:swot.20250921084241.1: *3* 方式一: RestTemplate -> queryItemById
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    public Item queryItemById(Long id) {
        return restTemplate.getForObject(
                "http://app-item/item/" + id, Item.class);  // <1>
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // <1> app-item 是 service-item 在 Eureka 中注册的服务名。
    //@+node:swot.20250921115356.1: *3* 方式二: OkHttpClient -> queryItemByIdWithOkHttpClient -> OkHttpClient 本身不支持服务发现功能，需要自己实现
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public Item queryItemByIdWithOkHttpClient(Long id) throws IOException {
        // 使用 LoadBalancerClient 获取负载均衡的实例
        ServiceInstance instance = loadBalancerClient.choose("app-item");
        String actualUrl = "http://" + instance.getHost() + ":" + instance.getPort() + "/item/" + id;
        Request request = new Request.Builder().url(actualUrl).build();
        try (Response response = okHttpClient.newCall(request).execute()) {  // 执行 OkHttpClient 调用
            String json = response.body().string();  // 读取响应体
            // 使用注入的 objectMapper 反序列化成 JSON 字符串
            return objectMapper.readValue(json, Item.class);
        }
    }
    //@+doc
    // ----
    //
    //@+node:swot.20250921085305.1: *3* 方式三: WebClient -> queryItemByIdWithWebClient
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    public Item queryItemByIdWithWebClient(Long id) {
        return webClient.get()
                        .uri("/{id}", id)
                        .retrieve()
                        .bodyToMono(Item.class)
                        .block();
    }
    //@+doc
    // ----
    //
    //
    //@-others
}
//@@language asciidoc
//@+doc
// ----
//@-others
//@-leo
