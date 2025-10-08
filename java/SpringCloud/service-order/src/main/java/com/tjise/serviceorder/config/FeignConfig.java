//@+leo-ver=5-thin
//@+node:swot.20251008102224.1: * @file service-order/src/main/java/com/tjise/serviceorder/config/FeignConfig.java
//@@language java
//@+others
//@+node:swot.20251008102433.1: ** @ignore-node import
package com.tjise.serviceorder.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//@+node:swot.20251008102445.1: ** @ignore-node class FeignConfig
//@+doc
// [source,java]
// ----
//@@c
//@@language java
@Configuration
public class FeignConfig {
    //@+others
    //@+node:swot.20251008102606.1: *3* @ignore-node Logger.Level
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    //@+doc
    // ----
    //
    //@+node:swot.20251008102643.1: *3* Retryer retryer -> New Added 重试机制
    //@+doc
    // [source,java]
    // ----
    //@@c
    //@@language java
    @Bean
    Retryer retryer() {
        return new Retryer.Default();
    }
    //@+doc
    // ----
    //
    //@-others
}
//@+doc
// ----
//
//@-others
//@-leo
