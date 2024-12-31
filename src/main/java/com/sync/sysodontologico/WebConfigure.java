package com.sync.sysodontologico;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigure implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/src/img/exam/**")
                .addResourceLocations("file:E:/Java/Projetos SpringBoot/SysOdontologico/src/main/resources/static/src/img/exam/")
                .setCachePeriod(3600); // 1 hora de cache
    }
}
