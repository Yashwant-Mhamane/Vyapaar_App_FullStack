package com.vyapaar.ApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfiguration {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/userauth/**").uri("lb://eurekaVyapaarAuthClient"))
                .route(p -> p.path("/vyapaar/**").uri("lb://eurekaVyapaarClient"))
                .build();
    }
}
