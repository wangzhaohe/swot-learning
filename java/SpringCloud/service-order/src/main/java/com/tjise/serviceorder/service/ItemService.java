//@+leo-ver=5-thin
//@+node:swot.20251001074653.1: * @file service-order/src/main/java/com/tjise/serviceorder/service/ItemService.java
//@@language java
//@+others
//@+node:swot.20251001074653.2: ** @ignore-node import
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
//@+node:swot.20251001074653.3: ** class ItemService
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
    // 包含其他代码
    //@+others
    //@+node:swot.20251001084311.1: *3* @ignore-node ItemService 单个构造方法注入
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
    //@+node:swot.20251001112815.1: *3* 方式一: RestTemplate -> queryItemById
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    public Item queryItemById(Long id) {

        // 获取实际被选择的实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("app-item");
        if (serviceInstance != null) {
            // String targetUrl = serviceInstance.getUri().toString() + "/item/" + id;
            // logger.info("Load Balancer: Requesting instance at " +
                    // serviceInstance.getHost() + ":" + serviceInstance.getPort() +
                    // " for item ID: " + id);
            System.out.println("负载均衡选择了端口: " + serviceInstance.getPort());
        }

        // restTemplate 会自动应用负载均衡，上面的实例选取只是为了能演示出负载均衡的策略。
        // 当服务不可用时，RestTemplate 会抛出异常，让断路器捕获
        Item item = restTemplate.getForObject(
                "http://app-item/item/" + id, Item.class);  // <1>

        // logger.info("Load Balancer: Got response fro item ID: " + id +
                    // ", result: " + (item != null ? "SUCCESS" : "FAILED"));
        return item;
    }
    //@@language asciidoc
    //@+doc
    // ----
    //
    // <1> app-item 是 service-item 在 Eureka 中注册的服务名。
    //@+node:swot.20251001074653.5: *3* @ignore-node 方式二: OkHttpClient -> queryItemByIdWithOkHttpClient -> OkHttpClient 本身不支持服务发现功能，需要自己实现
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
    //@+node:swot.20251001074653.6: *3* 方式三: WebClient    -> queryItemByIdWithWebClient
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    public Item queryItemByIdWithWebClient(Long id) {
        try {
            return webClient.get()
                            .uri("/{id}", id)
                            .retrieve()
                            .bodyToMono(Item.class)
                            .block();  // 在同步方法中使用 block
        } catch (org.springframework.web.reactive.function.client.WebClientResponseException.ServiceUnavailable e) {
            // 当负载均衡找不到服务实例时，抛出 RuntimeException 让断路器捕获
            throw new RuntimeException("Service app-item is unavailable", e);
        } catch (org.springframework.web.reactive.function.client.WebClientResponseException e) {
            // 其他 WebClient 异常也转换为 RuntimeException
            throw new RuntimeException("WebClient request failed", e);
        }
    }
    //@+doc
    // ----
    //@-others
}
//@@language asciidoc
//@+doc
// ----
//@-others
//@-leo
