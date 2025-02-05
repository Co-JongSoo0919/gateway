package com.devopsflow.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth_route", r -> r.path("/auth/**").filters(f -> f.rewritePath("/portal/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8080"))
                .route("portal_route", r -> r.path("/portal/**").filters(f -> f.rewritePath("/portal/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8081"))
                .build();
    }
}
