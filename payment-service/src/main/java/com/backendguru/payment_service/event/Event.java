package com.backendguru.payment_service.event;

import com.backendguru.payment_service.saga.SagaOrderRequest;
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