package com.backendguru.microservice.order.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderEventProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Sipariş oluşturuldu olayı üretiliyor => {}", event);
        kafkaTemplate.send(KafkaTopicConfig.ORDERS_TOPIC, event.getOrderId().toString(), event);
        // orderId'yi anahtar olarak göndermek, daha sonra gerekirse bölümlemeye yardımcı olabilir
    }
}
