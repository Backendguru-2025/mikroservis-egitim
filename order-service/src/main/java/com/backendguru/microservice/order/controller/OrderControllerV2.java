package com.backendguru.microservice.order.controller;

import com.backendguru.microservice.order.NewOrderRequest;
import com.backendguru.microservice.order.OrderResponse;
import com.backendguru.microservice.order.OrderService;
import com.backendguru.microservice.order.saga.OrderOrchestratorService;
import com.backendguru.microservice.order.saga.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/orders")
@RequiredArgsConstructor // Lombok constructor enjeksiyonu için bunu ekleyin
public class OrderControllerV2 {

    private final OrderOrchestratorService orderOrchestratorService;

    //@RolesAllowed("orders.write")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // HTTP durumunu 201 olarak ayarlar
    public String createProduct(@RequestBody OrderRequest order) {
        return orderOrchestratorService.placeOrder(order);
    }

}