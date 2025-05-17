package com.backendguru.microservice.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service // Bunu bir Spring servis bileşeni olarak işaretler
@RequiredArgsConstructor // Lombok: final alanlar için bir constructor oluşturur (DI)
public class UserService {


    private final UserRepository userRepository;

    // Tüm ürünleri getirme metodu
    public List<UserResponse> getAllUsers() {
        var userEntities = userRepository.findAll();
        return userEntities.stream().map(this::toResponse).toList(); // JpaRepository'nin findAll metodunu kullanır
    }

    //mapper function
    private UserResponse toResponse(UserEntity e) {
        return new UserResponse(
            e.getUsername(),
            e.getEmail(),
            e.getFirstName(),
            e.getLastName()
        );
    }
   
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(this::toResponse); // JpaRepository'nin findById metodunu kullanır
    }

}
