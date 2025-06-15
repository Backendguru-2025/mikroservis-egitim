package com.backendguru.inventory_service.saga;

import lombok.Data;

@Data
public class SagaOrderRequest {
    private String orderId;
    private String productId;
    private int quantity;
    private double amount;

}
