//@+leo-ver=5-thin
//@+node:swot.20251007091447.1: * @file service-order/src/main/java/com/tjise/serviceorder/config/FeignConfig.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.serviceorder.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
//@+doc
// ----
//@-leo
