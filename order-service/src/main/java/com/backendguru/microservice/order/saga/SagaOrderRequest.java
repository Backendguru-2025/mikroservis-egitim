package com.backendguru.microservice.order.saga;

import lombok.Data;

@Data
public class SagaOrderRequest {
    private String orderId;
    private String productId;
    private int quantity;
    private double amount;

}
