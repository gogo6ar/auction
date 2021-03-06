package com.auction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements  WebMvcConfigurer {

  @Value("${spring.client.url}")
  private String[] client;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
        //.exposedHeaders("access_token", "refresh_token")
        .allowedHeaders("Origin", "Content-Type", "Accept",
                        "Authorization", "Access-Control-Allow-Origin",
                        "Access-Control-Request-Method", "Access-Control-Request-Headers",
                        "X-Request-With")
        .allowedOrigins(client);
  }
}
