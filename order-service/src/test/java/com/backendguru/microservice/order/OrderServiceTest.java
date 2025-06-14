package com.backendguru.microservice.order;

import com.backendguru.microservice.order.client.product.ProductClientFacade;
import com.backendguru.microservice.order.client.product.ProductResponse;
import com.backendguru.microservice.order.client.user.UserClientFacade;
import com.backendguru.microservice.order.event.OrderEventProducer;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductClientFacade productClientFacade;
    @Mock
    private UserClientFacade userClientFacade;
    @Mock
    private MeterRegistry meterRegistry;
    @Mock
    private OrderEventProducer orderEventProducer;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder() {
        //GIVEN
        ProductResponse productResponse = new ProductResponse(2, "", "", BigDecimal.ONE);
        when(productClientFacade.getProduct(1L)).thenReturn(productResponse);

        //WHEN
        //orderService.createOrder(new NewOrderRequest(1L, 1, 1));

        //THEN
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> orderService.createOrder(new NewOrderRequest(1L, 1, 1))
        );
        assertEquals("Product not found productId: 1", runtimeException.getMessage());
    }
}