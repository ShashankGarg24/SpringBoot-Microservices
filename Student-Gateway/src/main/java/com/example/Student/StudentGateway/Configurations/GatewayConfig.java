package com.example.Student.StudentGateway.Configurations;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {


    //Setting up routes for microservices
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/students-info")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://localhost:8082"))
                .route(p -> p
                        .path("/api/student/**")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/api/response")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/api/getAll")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://localhost:8081"))
                .build();


    }


}
