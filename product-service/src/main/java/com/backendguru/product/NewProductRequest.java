package com.backendguru.product;

import java.math.BigDecimal;

public record NewProductRequest(
    String name, 
    String description,
    BigDecimal price) {

}
