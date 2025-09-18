//@+leo-ver=5-thin
//@+node:swot.20250916155249.1: * @file eureka/src/main/java/com/tjise/eureka/EurekaApplication.java
//@@language java
package com.tjise.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server启动类
 * 
 * @EnableEurekaServer 注解启用 Eureka Server 功能
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
//@-leo
