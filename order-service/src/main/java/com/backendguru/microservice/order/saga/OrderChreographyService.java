package com.backendguru.microservice.order.saga;

import com.backendguru.microservice.order.event.Event;
import com.backendguru.microservice.order.event.KafkaTopicConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderChreographyService {

    private final KafkaTemplate<String, Event> kafkaTemplate;
    // ... sipariş durumunu kaydetmek için repository (bellek içi veya gerçek)

    public String createOrder(SagaOrderRequest orderRequest) {
        orderRequest.setOrderId(UUID.randomUUID().toString());
        // 1. Siparişi VT'ye BEKLEMEDE durumuyla kaydet
        System.out.println("Sipariş veritabanına kaydedildi: " + orderRequest.getOrderId());
        // 2. ORDER_CREATED_EVENT'i Kafka'ya yayınla
        Event event = new Event("ORDER_CREATED", orderRequest);
        kafkaTemplate.send(KafkaTopicConfig.SAGA_TOPIC, event);
        System.out.println("Sipariş Oluşturuldu Olayı yayınlandı: " + orderRequest.getOrderId());
        return "Sipariş alındı, işleniyor...";
    }

    @KafkaListener(topics = KafkaTopicConfig.SAGA_TOPIC, groupId = "order-group")
    public void handleSagaEvents(Event event) {
        if ("PAYMENT_COMPLETED".equals(event.getType())) {
            System.out.println("Sipariş TAMAMLANDI: " + event.getOrderRequest().getOrderId());
            // Sipariş durumunu VT'de TAMAMLANDI olarak güncelle
        } else if (event.getType().endsWith("_COMPENSATION")) {
            System.out.println("Sipariş BAŞARISIZ (telafi olayı alındı): " + event.getOrderRequest().getOrderId());
            // Sipariş durumunu VT'de BAŞARISIZ olarak güncelle
        }
    }
}
