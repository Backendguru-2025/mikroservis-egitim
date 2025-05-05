package com.backendguru.product;

import java.math.BigDecimal;

public record ProductResponse(
    long productId, 
    String name, 
    String description,
    BigDecimal price) {

}
