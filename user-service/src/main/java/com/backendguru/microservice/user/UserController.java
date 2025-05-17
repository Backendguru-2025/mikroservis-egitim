package com.backendguru.microservice.user;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor // Lombok constructor enjeksiyonu için bunu ekleyin
public class UserController{

    private final UserService userService;

    @GetMapping(value = "/{productId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getProduct(@PathVariable Long userId) throws Exception{
        Optional<UserResponse> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok) // Eğer varsa, 200 OK içine sar
                      .orElse(ResponseEntity.notFound().build()); // Eğer yoksa, 404 döndür
    }

    @GetMapping
    public List<UserResponse> getAllProducts() {
        return userService.getAllUsers();
    }

}