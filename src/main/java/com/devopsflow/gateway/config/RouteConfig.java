package com.devopsflow.gateway.config;

import com.devopsflow.gateway.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth_route", r -> r.path("/auth/**").filters(f -> f.rewritePath("/auth/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8080"))
                .route("portal_route", r -> r.path("/portal/**").filters(f -> f.rewritePath("/portal/(?<segment>.*)", "/${segment}").filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config())))
                        .uri("http://localhost:8081"))
                .build();
    }
}
