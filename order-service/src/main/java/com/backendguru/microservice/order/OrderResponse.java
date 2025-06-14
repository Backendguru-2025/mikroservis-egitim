package com.backendguru.microservice.order;

public record OrderResponse(
    Long productId,
    Integer userId,
    Integer quantity
) {
    
}
