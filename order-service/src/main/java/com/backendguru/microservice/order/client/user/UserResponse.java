package com.backendguru.microservice.order.client.user;

public record UserResponse(
    String username,
    String email,
    String firstName,
    String lastName
) {
    
}
