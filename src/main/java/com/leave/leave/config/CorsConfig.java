package com.leave.leave.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Allow CORS for all paths
                        .allowedOrigins("*")  // Allow all origins, change it to specific domains if needed
                        .allowedMethods("*")  // Allowed methods
                        .allowedHeaders("*")  // Allow all headers
                        .maxAge(3600);  // Cache preflight response for 1 hour
            }
        };
    }
}
