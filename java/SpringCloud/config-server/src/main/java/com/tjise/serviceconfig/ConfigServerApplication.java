//@+leo-ver=5-thin
//@+node:swot.20251023075811.1: * @file config-server/src/main/java/com/tjise/serviceconfig/ConfigServerApplication.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.serviceconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer  // --New Added--
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
//@+doc
// ----
//
//@-leo
