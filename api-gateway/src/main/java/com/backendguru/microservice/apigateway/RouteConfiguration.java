package com.backendguru.microservice.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                    r -> r.path("/web/products/**")
                    .filters(f ->
                            f.rewritePath("/web/?(?<segment>.*)", "/v1/$\\{segment}")
                            .removeResponseHeader("Date")
                            )
                    
                    .uri("lb://PRODUCT-SERVICE:8080")
                )
                .build();
    }

    /*
     *   cloud:
    gateway:
      routes:
      - id: rewritepath_route
        uri: http://product-service:8080
        predicates:
        - Path=/mobile/products/**
#        - Path=/web/products/**
        filters:
        - RewritePath=/mobile/?(?<segment>.*), /v1/$\{segment}   
     */
    
}
