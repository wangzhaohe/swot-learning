//@+leo-ver=5-thin
//@+node:swot.20251015114451.1: * @file gateway/src/main/java/com/tjise/gateway/filter/ResponseTimeGlobalFilter.java
//@@language java
package com.tjise.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// 全局 filter 只需要放在 IOC Bean 容器中即可，不用再注入。
@Component
@Slf4j
public class ResponseTimeGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String uri = request.getURI().toString();
        long start= System.currentTimeMillis();
        log.info("请求『{}』开始：时间：{}", uri, start);

        Mono<Void> filter = chain.filter(exchange)   // 放行请求
                .doFinally((result)->{      // 响应式编程的异步链式调用
                    long end = System.currentTimeMillis();
                    log.info("请求『{}』结束：时间：{}，耗时：{}ms", uri, end, end-start);
                });
        return filter;
    }

    @Override
    public int getOrder() {
        return 0;  // 数字越小，优先级越高
    }
}
//@-leo
