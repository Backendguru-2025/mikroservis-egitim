package com.backendguru.microservice.order;

import java.util.List;
import java.util.Optional;

import com.backendguru.microservice.order.client.product.ProductClientFacade;
import com.backendguru.microservice.order.client.product.ProductResponse;
import com.backendguru.microservice.order.client.user.UserClientFacade;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.backendguru.microservice.order.client.user.UserResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // Bunu bir Spring servis bileşeni olarak işaretler
@RequiredArgsConstructor // Lombok: final alanlar için bir constructor oluşturur (DI)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClientFacade productClientFacade;
    private final UserClientFacade userClientFacade;
    private final MeterRegistry meterRegistry;

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
        // kullanici var mi ?
        // urun var mi ?
        checkProduct(newOrderRequest);
        checkUser(newOrderRequest);


        var orderEntity = new OrderEntity();
        orderEntity.setProductId(newOrderRequest.productId());
        orderEntity.setUserId(newOrderRequest.userId());
        orderEntity.setQuantity(newOrderRequest.quantity());
        OrderEntity order = orderRepository.save(orderEntity);

        Counter counter = meterRegistry.counter("orders.placed", "userId", order.getUserId().toString());
        counter.increment();
        return toResponse(order); // JpaRepository'nin save metodunu kullanır
    }

    private void checkProduct(NewOrderRequest newOrderRequest) {
        ProductResponse foundProduct = productClientFacade.getProduct(newOrderRequest.productId());
        if (foundProduct.productId() != newOrderRequest.productId()) {
            throw new RuntimeException("Product not found productId: " + newOrderRequest.productId());
        }
    }

    private void checkUser(NewOrderRequest newOrderRequest) {
        UserResponse foundUser = userClientFacade.getUser(newOrderRequest.userId());
        if (foundUser.username().equals("0") || foundUser.username().equals("-1")) {
            throw new RuntimeException("User not found userId: " + newOrderRequest.userId());
        }
    }

}
