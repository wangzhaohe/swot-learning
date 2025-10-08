//@+leo-ver=5-thin
//@+node:swot.20251008203143.1: * @file service-order/src/main/java/com/tjise/serviceorder/interceptor/XTokenRequestInterceptor.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.serviceorder.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component  // -- New Added -- 会自动应用 IOC 容器中的 RequestInterceptor 组件
public class XTokenRequestInterceptor implements RequestInterceptor {
    /**
     * 请求拦截器
     * @param requestTemplate 请求模板
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("请求拦截器...");
//        requestTemplate.header("X-Token", "123456");
        requestTemplate.header("X-Token", UUID.randomUUID().toString());
    }
}
//@+doc
// ----
//
//@-leo
