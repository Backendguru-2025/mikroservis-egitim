package com.backendguru.microservice.user;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.backendguru.microservice.user.model.NewUserRequest;
import com.backendguru.microservice.user.model.UserResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor // Lombok constructor enjeksiyonu için bunu ekleyin
public class UserController{

    private final UserService userService;

    @GetMapping(value = "/{userId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) throws Exception{
        Optional<UserResponse> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok) // Eğer varsa, 200 OK içine sar
                      .orElse(ResponseEntity.notFound().build()); // Eğer yoksa, 404 döndür
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody NewUserRequest newUserRequest){
        Long userId = userService.registerUser(newUserRequest);
        return ResponseEntity.ok(Collections.singletonMap("userId", userId));
    }
}