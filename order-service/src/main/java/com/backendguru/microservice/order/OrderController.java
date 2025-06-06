package com.backendguru.microservice.order;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor // Lombok constructor enjeksiyonu için bunu ekleyin
public class OrderController{

    private final OrderService orderService;

    @GetMapping(value = "/{orderId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) throws Exception{
        Optional<OrderResponse> user = orderService.getOrderById(orderId);
        return user.map(ResponseEntity::ok) // Eğer varsa, 200 OK içine sar
                      .orElse(ResponseEntity.notFound().build()); // Eğer yoksa, 404 döndür
    }

    @GetMapping
    public List<OrderResponse> getAllProducts() {
        return orderService.getAllOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // HTTP durumunu 201 olarak ayarlar
    public OrderResponse createProduct(@RequestBody NewOrderRequest order) {
        return orderService.createOrder(order);
    }

}