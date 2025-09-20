//@+leo-ver=5-thin
//@+node:swot.20250919222308.1: * @file service-order/src/main/java/com/tjise/serviceorder/ServiceOrderApplication.java
//@@language java
//@+others
//@+node:swot.20250919222308.2: ** @ignore-node import
package com.tjise.serviceorder;

import com.tjise.serviceorder.utils.ItemProperties;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;
//@+node:swot.20250919222308.3: ** class ServiceOrderApplication
/**
 * 订单服务启动类
 * Spring Boot 应用程序入口点
 */
@SpringBootApplication
@EnableEurekaClient  // new -> 启用 Eureka 客户端功能
public class ServiceOrderApplication {
    // @Autowired
    // private ItemProperties itemProperties;  // 新增 DI 注入 配置的 url
    // 使用 Eureka 找服务名的方式，就用不到该对象来找配置参数了。

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
    //@+others
    //@+node:swot.20250919222308.4: *3* RestTemplate
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
    @LoadBalanced // new -> 使用负载均衡
    public RestTemplate restTemplate() {
        // 可以在这里添加拦截器来统一处理URL前缀
        // return new RestTemplate();  // not use OkHttp
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());  // use OkHttp
    }
    //@+doc
    // ----
    //@+node:swot.20250919222308.5: *3* OkHttpClient
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Bean
    @LoadBalanced // new -> 使用负载均衡
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    }
    //@+doc
    // ----
    //
    //@+node:swot.20250919222308.6: *3* WebClient
    //@+doc
    //@@nowrap
    // [source,java]
    // ----
    //@@c
    //@@language java
    // 直接注入也可以的
    // public WebClient webClient(ItemServiceProperties properties) {
    @Bean
    @LoadBalanced // new -> 使用负载均衡
    public WebClient webClient() {
        return WebClient.builder()
            // .baseUrl(itemProperties.getUrl())   // 使用注入的 Url
            .baseUrl("http://app-item/item")  // 改成使用 eureka 注册中心调用(去注册中心根据 app-item 查找服务，这种方式必须先开启负载均衡 @LoadBalanced)
            .build();
    }
    //@+doc
    // ----
    //@-others
}
//@-others
//@-leo
