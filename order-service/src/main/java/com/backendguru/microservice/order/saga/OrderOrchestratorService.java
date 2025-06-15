package com.backendguru.microservice.order.saga;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderOrchestratorService {

    private final RestTemplate restTemplate;

    private final String INVENTORY_URL = "http://inventory-service:8080/inventory";
    private final String PAYMENT_URL = "http://payment-service:8080/payment";

    public String placeOrder(OrderRequest orderRequest) {
        System.out.println("Orkestrasyon başlatıldı: " + orderRequest.orderId());

        // 1. Envanteri Rezerve Et
        try {
            System.out.println("Envanter rezervasyonu deneniyor...");
            ResponseEntity<String> inventoryResponse = restTemplate.postForEntity(
                    INVENTORY_URL + "/reserve", orderRequest, String.class);
            if (!inventoryResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Envanter rezervasyonu başarısız oldu");
            }
            System.out.println("Envanter başarıyla rezerve edildi.");
        } catch (Exception e) {
            System.out.println("Orkestrasyon hatası: Envanter rezerve edilemedi. " + e.getMessage());
            return "Sipariş başarısız: Envanter rezerve edilemedi.";
        }

        // 2. Ödemeyi İşle
        try {
            System.out.println("Ödeme işlemi deneniyor...");
            ResponseEntity<String> paymentResponse = restTemplate.postForEntity(
                    PAYMENT_URL + "/process", orderRequest, String.class);
            if (!paymentResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Ödeme işlemi başarısız oldu: " + paymentResponse.getBody());
            }
            System.out.println("Ödeme başarıyla işlendi.");
        } catch (Exception e) {
            System.out.println("Ödeme başarısız, envanter telafi ediliyor... Hata: " + e.getMessage());
            restTemplate.postForEntity(INVENTORY_URL + "/compensate", orderRequest, String.class);
            return "Sipariş başarısız: Ödeme işlemi hatası.";
        }

        System.out.println("Orkestrasyon başarıyla tamamlandı.");
        return "Sipariş başarıyla oluşturuldu!";
    }
}

