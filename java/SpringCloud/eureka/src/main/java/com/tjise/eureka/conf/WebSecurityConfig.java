//@+leo-ver=5-thin
//@+node:swot.20250921180203.1: * @file eureka/src/main/java/com/tjise/eureka/conf/WebSecurityConfig.java
//@@language java
//@+others
//@+node:swot.20250921180537.1: ** @ignore-node import
package com.tjise.eureka.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
//@+node:swot.20250921180605.1: ** WebSecurityConfig 新建安全配置类
//@+doc
// [source,java]
// ----
//@@c
//@@language java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
            .and()
            .csrf().disable()
            .authorizeHttpRequests(authz -> authz
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
//@+doc
// ----
//@-others
//@-leo
