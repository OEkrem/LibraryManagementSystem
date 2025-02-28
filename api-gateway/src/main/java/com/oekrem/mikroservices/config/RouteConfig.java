package com.oekrem.mikroservices.config;

/*
    İstersen application.yml yerine burada da belirtilebilir


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("book-service", r -> r.path("/api/books/**")
                        .uri("http://localhost:8081"))
                .route("user-service", r -> r.path("/api/users/**")
                        .uri("http://localhost:8082"))
                .route("borrow-service", r -> r.path("/api/borrow/**")
                        .uri("http://localhost:8083"))
                .build();
    }

}
*/