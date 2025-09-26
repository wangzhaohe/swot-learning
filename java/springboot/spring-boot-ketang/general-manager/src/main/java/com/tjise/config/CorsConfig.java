//@+leo-ver=5-thin
//@+node:swot.20250918093531.1: * @file src/main/java/com/tjise/config/CorsConfig.java
//@+doc
// [source,java]
// ----
//@@c
//@@language java
//@@language java
package com.tjise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        System.out.println("[CORS CONFIG] " + java.time.LocalDateTime.now() + " - Initializing CORS Filter");
        CorsConfiguration config = new CorsConfiguration();
        // 允许所有域名进行跨域调用
        config.addAllowedOriginPattern("*");
        // 允许所有请求头
        config.addAllowedHeader("*");
        // 允许所有方法（POST、GET...）
        config.addAllowedMethod("*");
        // 允许携带 Cookie
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        System.out.println("[CORS CONFIG] " + java.time.LocalDateTime.now() + " - CORS Filter configured");
        return new CorsFilter(source);
    }
}
//@+doc
// ----
//@-leo
