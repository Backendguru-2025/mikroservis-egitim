package com.backendguru.microservice.user.repository;

import com.backendguru.microservice.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Data // Lombok: getter, setter, toString(), equals(), hashCode() üretir
@NoArgsConstructor // Lombok: JPA tarafından proxy oluşturma için gereklidir
@AllArgsConstructor // Lombok: Nesne oluşturmak için kullanışlıdır
@Builder // Lombok: Opsiyonel builder pattern desteği sağlar
public class UserEntity {
    @Id // Bu alanı birincil anahtar (primary key) olarak işaretler
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID için otomatik artışı yapılandırır (H2 bunu destekler)
    private Long id;

    private String username;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;

    public static UserEntity  fromDomain(User user){
        return UserEntity.builder()
                .email(user.getEmail().getValue())
                .passwordHash(user.getPassword().getHashedValue())
                .build();
    }

}
