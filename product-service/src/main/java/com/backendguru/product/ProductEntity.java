package com.backendguru.product;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Lombok: getter, setter, toString(), equals(), hashCode() üretir
@NoArgsConstructor // Lombok: JPA tarafından proxy oluşturma için gereklidir
@AllArgsConstructor // Lombok: Nesne oluşturmak için kullanışlıdır
@Builder // Lombok: Opsiyonel builder pattern desteği sağlar
public class ProductEntity {
    @Id // Bu alanı birincil anahtar (primary key) olarak işaretler
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID için otomatik artışı yapılandırır (H2 bunu destekler)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price; // Para birimi için BigDecimal kullanın
}
