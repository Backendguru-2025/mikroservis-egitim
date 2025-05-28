package com.backendguru.microservice.notification.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationConsumer {

    public static final String ORDERS_TOPIC = "order.events"; // Üreticinin topic'iyle eşleşmeli

    @KafkaListener(topics = ORDERS_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) {
        log.info(String.format("Sipariş oluşturuldu olayı alındı => %s", event));
        // Bir bildirim göndermeyi simüle etme
        log.info(String.format("Sipariş için bildirim gönderiliyor: %s, müşteri: %s", event.getOrderId(), event.getCustomerId()));
    }
}
