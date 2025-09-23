//@+leo-ver=5-thin
//@+node:swot.20241105084957.1: * @file src/main/java/com/tjise/config/MyMvcConfig.java
//@@language java
//@+doc
// [source,java]
// ----
//@@c
package com.tjise.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 此注解表明该类是 Spring 配置类。Spring 会自动扫描并注册该类，使其可以作为应用上下文的一部分。
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    // WebMvcConfigurer 是一个接口，允许用户自定义 Spring MVC 的配置。通过实现这个接口，你可以覆盖默认配置或添加自定义配置。

    // 配置虚拟路径映射访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射图片保存地址
        registry.addResourceHandler("/uploads/**")  // 增加 url 前缀
                .addResourceLocations("file:/Users/swot/swot-learning/java/springboot/spring-boot-ketang/general-manager/uploads/");  // 获取图片的路径
    }
}
//@+doc
// ----
//@-leo
