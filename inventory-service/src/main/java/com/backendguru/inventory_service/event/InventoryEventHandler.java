package com.backendguru.inventory_service.event;

import com.backendguru.inventory_service.OrderRequest;
import com.backendguru.inventory_service.saga.SagaOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Component
public class InventoryEventHandler {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    private Map<String, Integer> inventory = new ConcurrentHashMap<>(Map.of("product-1", 10));

    @KafkaListener(topics = "saga-topic", groupId = "inventory-group")
    public void handleSagaEvents(Event event) {
        SagaOrderRequest orderRequest = event.getOrderRequest();
        if ("ORDER_CREATED".equals(event.getType())) {
            System.out.println("Envanter servisi ORDER_CREATED olayını aldı: " + orderRequest.getOrderId());
            if (inventory.getOrDefault(orderRequest.getProductId(), 0) >= orderRequest.getQuantity()) {
                inventory.compute(orderRequest.getProductId(), (k, v) -> v - orderRequest.getQuantity());
                kafkaTemplate.send("saga-topic", new Event("INVENTORY_RESERVED", orderRequest));
                System.out.println("Envanter rezerve edildi ve INVENTORY_RESERVED olayı yayınlandı.");
            } else {
                kafkaTemplate.send("saga-topic", new Event("INVENTORY_COMPENSATION", orderRequest));
                System.out.println("Envanter yetersiz, INVENTORY_COMPENSATION olayı yayınlandı.");
            }
        } else if ("PAYMENT_FAILED".equals(event.getType())) {
            inventory.compute(orderRequest.getProductId(), (k, v) -> v + orderRequest.getQuantity());
            kafkaTemplate.send("saga-topic", new Event("INVENTORY_COMPENSATION", orderRequest));
            System.out.println("Ödeme hatası nedeniyle envanter telafi edildi ve INVENTORY_COMPENSATION olayı yayınlandı.");
        }
    }
}
