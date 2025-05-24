package com.backendguru.microservice.user;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.backendguru.microservice.user.domain.*;
import com.backendguru.microservice.user.model.NewUserRequest;
import com.backendguru.microservice.user.model.UserResponse;
import com.backendguru.microservice.user.repository.UserEntity;
import com.backendguru.microservice.user.repository.UserRepository;
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

    public Long registerUser(NewUserRequest newUserRequest) {
        Email email = new Email(newUserRequest.email());
        Password password = new Password(newUserRequest.password());
        User user = new User(null, email, password, null, null, null);
        user.addRole(new Role(null, "NORMAL_USER", Set.of(Privilege.LOGIN, Privilege.CREATE_ORDER)));

        UserEntity newUserEntity = userRepository.save(UserEntity.fromDomain(user));

        return newUserEntity.getId();
    }
}
