package com.backendguru.inventory_service.event;

import com.backendguru.inventory_service.saga.SagaOrderRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private String type;
    private SagaOrderRequest orderRequest;
}