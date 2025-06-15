package com.backendguru.payment_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody OrderRequest request) {
        System.out.println("Gelen ödeme isteği: " + request.orderId());
        if (request.amount() > 100.0) {
            System.out.println("HATA: Ödeme başarısız oldu: " + request.orderId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ödeme başarısız: Tutar çok yüksek");
        }
        System.out.println("Ödeme işlendi: " + request.orderId());
        return ResponseEntity.ok("Ödeme başarıyla işlendi");
    }

    @PostMapping("/compensate")
    public ResponseEntity<String> compensatePayment(@RequestBody OrderRequest request) {
        System.out.println("Ödeme telafi edildi (iade edildi): " + request.orderId());
        return ResponseEntity.ok("Ödeme telafi edildi");
    }
}
