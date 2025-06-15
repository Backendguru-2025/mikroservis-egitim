package com.backendguru.microservice.order.controller;

import com.backendguru.microservice.order.saga.OrderChreographyService;
import com.backendguru.microservice.order.saga.OrderOrchestratorService;
import com.backendguru.microservice.order.saga.OrderRequest;
import com.backendguru.microservice.order.saga.SagaOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v3/orders")
@RequiredArgsConstructor // Lombok constructor enjeksiyonu için bunu ekleyin
public class OrderControllerV3 {

    private final OrderChreographyService orderChreographyService;

    //@RolesAllowed("orders.write")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // HTTP durumunu 201 olarak ayarlar
    public String createProduct(@RequestBody SagaOrderRequest order) {
        return orderChreographyService.createOrder(order);
    }

}