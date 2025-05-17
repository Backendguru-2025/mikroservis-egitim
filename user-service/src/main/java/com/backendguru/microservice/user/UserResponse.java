package com.backendguru.microservice.user;

public record UserResponse(
    String username,
    String email,
    String firstName,
    String lastName
) {
    
}
