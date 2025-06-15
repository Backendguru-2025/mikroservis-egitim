package com.backendguru.inventory_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private Map<String, Integer> inventory = new ConcurrentHashMap<>(
            Map.of(
                    "product-1", 10));

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveInventory(@RequestBody OrderRequest request) {
        String productId = request.productId();
        int quantity = request.quantity();

        System.out.println("Gelen envanter rezervasyon isteği: " + request.orderId());
        if (inventory.getOrDefault(productId, 0) >= quantity) {
            inventory.compute(productId, (k, v) -> v - quantity);
            System.out.println("Envanter rezerve edildi: " + request.orderId());
            return ResponseEntity.ok("Envanter rezerve edildi");
        } else {
            System.out.println("HATA: Yetersiz envanter: " + request.orderId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Yetersiz envanter");
        }
    }

    @PostMapping("/compensate")
    public ResponseEntity<String> compensateInventory(@RequestBody OrderRequest request) {
        String productId = request.productId();
        int quantity = request.quantity();
        inventory.compute(productId, (k, v) -> v + quantity);
        System.out.println("Envanter rezervasyonu telafi edildi: " + request.orderId());
        return ResponseEntity.ok("Envanter telafi edildi");
    }
}
