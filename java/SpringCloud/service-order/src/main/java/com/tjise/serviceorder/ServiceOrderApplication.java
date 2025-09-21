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
//@+node:swot.20250919222308.3: ** class ServiceOrderApplication -> NOTE:下面的三个客户端任选一个即可
//@+doc
// [source,java]
// ----
//@@c
/**
 * 订单服务启动类，Spring Boot 应用程序入口点。
 */
@SpringBootApplication
@EnableEurekaClient  // new -> 启用 Eureka 客户端功能
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
    // 包含其他代码
    //@+others
    //@+node:swot.20250919222308.4: *3* 1. RestTemplate
    //@+doc
    // [source,java]
    // ----
    //@@c
    /**
     * 创建 RestTemplate 实例，用于调用其他微服务。
     * @return RestTemplate
     */
    @Bean
    @LoadBalanced // new -> 使用负载均衡
    public RestTemplate restTemplate() {
        // 可以在这里添加拦截器来统一处理URL前缀
        // return new RestTemplate();  // not use OkHttp
        return new RestTemplate(
               new OkHttp3ClientHttpRequestFactory());  // use OkHttp
    }
    //@+doc
    // ----
    //@+node:swot.20250919222308.5: *3* 2. OkHttpClient 不支持 @LoadBalanced
    //@+doc
    // [source,java]
    // ----
    //@@c
    @Bean
    // @LoadBalanced  // OkHttpClient 不支持负载均衡，在这儿写该注解没用。
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    }
    //@+doc
    // ----
    //
    //@+node:swot.20250919222308.6: *3* 3. WebClient
    //@+doc
    //@@nowrap
    // [source,java]
    // ----
    //@@c
    // 配置负载均衡的 WebClient.Builder
    @Bean
    @LoadBalanced // new -> 使用负载均衡
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    // 使用 Builder 创建 WebClient
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
            .baseUrl("http://app-item/item")  // 使用 eureka 注册中心调用(去注册中心查找服务 app-item，这种方式必须先开启负载均衡 @LoadBalanced)
            .build();
    }
    //@+doc
    // ----
    //
    // .总结
    // ****
    // WebClient 也需要使用 @LoadBalanced 注解，但需要注解在 WebClient.Builder 上，而不是 WebClient 实例上。
    // 这与 WebClient 的设计有关：
    //
    // - WebClient 是不可变的（immutable）
    // - WebClient.Builder 是可变的，用于构建 WebClient 实例
    // - Spring Cloud 需要在 Builder 层面注入负载均衡能力
    //
    // 这样设计是为了与 WebClient 的不可变性设计保持一致，同时也提供了更灵活的配置方式。
    // ****
    //@-others
}
//@+doc
// ----
//
// .负载均衡使用拦截器原理：
// ****
// 1. 拦截请求URL
// 2. 识别服务名
// 3. 通过服务发现获取实际地址
// 4. 替换URL并发起请求
// ****
//@-others
//@-leo
