package com.backendguru.microservice.order.client.product;

import java.math.BigDecimal;

public record ProductResponse(
    long productId, 
    String name, 
    String description,
    BigDecimal price) {

}
