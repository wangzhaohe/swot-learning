//@+leo-ver=5-thin
//@+node:swot.20251012180208.1: * @file gateway/src/main/java/com/tjise/gateway/GatewayMainApplication.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMainApplication.class, args);
    }
}
//@+doc
// ----
//
//@-leo
