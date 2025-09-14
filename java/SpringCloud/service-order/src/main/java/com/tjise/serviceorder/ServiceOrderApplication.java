//@+leo-ver=5-thin
//@+node:swot.20250914213134.1: * @file service-order/src/main/java/com/tjise/serviceorder/ServiceOrderApplication.java
//@@language java
//@+others
//@+node:swot.20250914213134.2: ** @ignore-node import
package com.tjise.serviceorder;

import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;
//@+node:swot.20250914213134.3: ** @ignore-node class ServiceOrderApplication
/**
 * 订单服务启动类
 * Spring Boot 应用程序入口点
 */
@SpringBootApplication
public class ServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
    //@+others
    //@+node:swot.20250914213134.4: *3* @ignore-node RestTemplate restTemplate
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
    //@+node:swot.20250914213134.5: *3* @ignore-node OkHttpClient okHttpClient
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
    //@+node:swot.20250914102436.1: *3* WebClient
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .baseUrl("http://127.0.0.1:8081")
            .build();
    }
    //@+doc
    // ----
    //
    //@-others
}
//@-others
//@-leo
