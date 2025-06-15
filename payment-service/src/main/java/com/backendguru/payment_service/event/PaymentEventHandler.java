package com.backendguru.payment_service.event;

import com.backendguru.payment_service.saga.SagaOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentEventHandler {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    @KafkaListener(topics = "saga-topic", groupId = "payment-group")
    public void handleSagaEvents(Event event) {
        SagaOrderRequest orderRequest = event.getOrderRequest();
        if ("INVENTORY_RESERVED".equals(event.getType())) {
            System.out.println("Ödeme servisi INVENTORY_RESERVED olayını aldı: " + orderRequest.getOrderId());
            if (orderRequest.getAmount() <= 100.0) {
                System.out.println("Ödeme başarılı, PAYMENT_COMPLETED olayı yayınlanıyor.");
                kafkaTemplate.send("saga-topic", new Event("PAYMENT_COMPLETED", orderRequest));
            } else {
                System.out.println("Ödeme başarısız, PAYMENT_FAILED olayı yayınlanıyor.");
                kafkaTemplate.send("saga-topic", new Event("PAYMENT_FAILED", orderRequest));
            }
        }
    }
}
