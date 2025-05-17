package com.backendguru.microservice.order;

public record NewOrderRequest(
    Long productId,
    Integer quantity,
    Integer userId
) {
    
}
