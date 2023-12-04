package com.cpr.abyblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许跨域访问的路径
                .allowedOriginPatterns("*")  // 允许跨域访问的源
                .allowedMethods("*")  // 允许的请求方法
                .allowedOriginPatterns("*")  // 允许跨域请求的域名
                .allowedHeaders("*") // 允许的请求头
                .allowCredentials(true)  // 是否允许证书（cookies）
                .maxAge(3600);  // 跨域允许时间
    }
}