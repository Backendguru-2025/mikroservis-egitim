package com.backendguru.microservice.order.saga;

public record OrderRequest(
    String orderId,
    String productId,
    int quantity,
    double amount
)
{

}

