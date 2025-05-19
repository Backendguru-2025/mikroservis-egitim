package com.backendguru.microservice.order.client.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/v1/users/{userId}", contentType= MediaType.APPLICATION_JSON_VALUE)
public interface UserClientService {

	@GetExchange
    UserResponse getUser(@PathVariable Integer userId);
    
} 

