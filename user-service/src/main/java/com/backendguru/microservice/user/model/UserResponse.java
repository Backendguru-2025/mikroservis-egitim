package com.backendguru.microservice.user.model;

public record UserResponse(
    String username,
    String email,
    String firstName,
    String lastName
) {
    
}
