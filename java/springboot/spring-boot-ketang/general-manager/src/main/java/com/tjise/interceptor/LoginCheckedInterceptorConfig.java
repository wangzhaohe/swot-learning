//@+leo-ver=5-thin
//@+node:swot.20250819070304.1: * @file src/main/java/com/tjise/interceptor/LoginCheckedInterceptorConfig.java
//@@language java
//@+others
//@+node:swot.20250831211856.1: ** @ignore-node import
package com.tjise.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@+node:swot.20250831212006.1: ** class LoginCheckedInterceptorConfig
//@+doc
// [source,java]
// ----
//@@c
/**
 * 注册拦截器
 */
@Configuration
public class LoginCheckedInterceptorConfig implements WebMvcConfigurer {
    // 其他代码
    //@+others
    //@+node:swot.20250831212202.1: *3* @ignore-node Override addInterceptors
    @Autowired
    private LoginCheckedInterceptor loginCheckedInterceptor;

    // 注册拦截方法
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        System.out.println("Enter addInterceptors()");
        registry.addInterceptor(loginCheckedInterceptor)
                .addPathPatterns("/**");  // 拦截所有
                //.excludePathPatterns("/login");
    }
    //@-others
    // 增加允许 CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("Enter addCorsMappings()");
        registry
            .addMapping("/**")      // 允许所有路由
            .allowedOriginPatterns("*")  // 允许任何跨域来源
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
            .allowedHeaders("*")    // 允许所有头
            .allowCredentials(true) // 允许携带 cookie
            .exposedHeaders("*");   // 暴露所有头
    }
}
//@+doc
// ----
//@-others
//@-leo
