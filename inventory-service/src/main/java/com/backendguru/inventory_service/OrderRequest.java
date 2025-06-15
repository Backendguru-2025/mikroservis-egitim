package com.backendguru.inventory_service;

public record OrderRequest(
    String orderId,
    String productId,
    int quantity,
    double amount
)
{

}

