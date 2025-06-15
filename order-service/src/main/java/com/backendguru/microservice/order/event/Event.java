package com.backendguru.microservice.order.event;

import com.backendguru.microservice.order.saga.SagaOrderRequest;
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