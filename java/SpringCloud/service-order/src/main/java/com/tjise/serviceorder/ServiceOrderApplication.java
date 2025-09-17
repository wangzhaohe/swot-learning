//@+leo-ver=5-thin
//@+node:swot.20250916113222.1: * @file service-order/src/main/java/com/tjise/serviceorder/ServiceOrderApplication.java
//@@language java
//@+others
//@+node:swot.20250916113222.2: ** @ignore-node import
package com.tjise.serviceorder;

import com.tjise.serviceorder.utils.ItemProperties;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;
//@+node:swot.20250916113222.3: ** class ServiceOrderApplication
/**
 * 订单服务启动类
 * Spring Boot 应用程序入口点
 */
@SpringBootApplication
public class ServiceOrderApplication {

    // 新增 DI 注入 配置的 url
    @Autowired
    private ItemProperties itemProperties;

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
    //@+others
    //@+node:swot.20250916113222.4: *3* @ignore-node RestTemplate restTemplate
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    /**
     * 创建RestTemplate实例
     * 用于调用其他微服务
     * 
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        // 可以在这里添加拦截器来统一处理URL前缀
        // return new RestTemplate();  // 未使用 OkHttp
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
    //@+doc
    // ----
    //@+node:swot.20250916113222.5: *3* @ignore-node OkHttpClient okHttpClient
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    }
    //@+doc
    // ----
    //
    //@+node:swot.20250916113222.6: *3* @Bean WebClient
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    // 直接注入也可以的
    // public WebClient webClient(ItemServiceProperties properties) {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .baseUrl(itemProperties.getUrl())   // 使用注入的 Url
            .build();
    }
    //@+doc
    // ----
    //
    //@-others
}
//@-others
//@-leo
