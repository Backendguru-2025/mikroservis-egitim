package com.backendguru.microservice.order;

public record OrderResponse(
    Long productId,
    Integer userdId,
    Integer quantity
) {
    
}
