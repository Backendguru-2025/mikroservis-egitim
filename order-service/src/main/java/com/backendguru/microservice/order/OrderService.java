package com.backendguru.microservice.order;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service // Bunu bir Spring servis bileşeni olarak işaretler
@RequiredArgsConstructor // Lombok: final alanlar için bir constructor oluşturur (DI)
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderResponse> getAllOrders() {
        var orderEntities = orderRepository.findAll();
        return orderEntities.stream().map(this::toResponse).toList(); // JpaRepository'nin findAll metodunu kullanır
    }

    //mapper function
    private OrderResponse toResponse(OrderEntity e) {
        return new OrderResponse(
            e.getProductId(),
            e.getUserId(),
            e.getQuantity()
        );
    }
   
    public Optional<OrderResponse> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::toResponse); // JpaRepository'nin findById metodunu kullanır
    }

    public OrderResponse createOrder(NewOrderRequest newOrderRequest) {
        // Kaydetmeden önce burada doğrulama veya iş mantığı eklenebilir
        var orderEntity = new OrderEntity();
        orderEntity.setProductId(newOrderRequest.productId());
        orderEntity.setUserId(newOrderRequest.userId());
        orderEntity.setQuantity(newOrderRequest.quantity());
        return toResponse(orderRepository.save(orderEntity)); // JpaRepository'nin save metodunu kullanır
    }

}
