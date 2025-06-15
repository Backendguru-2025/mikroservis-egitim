package com.backendguru.payment_service;

public record OrderRequest(
    String orderId,
    String productId,
    int quantity,
    double amount
)
{

}

