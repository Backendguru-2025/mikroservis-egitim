package com.backendguru.microservice.user.model;

import com.backendguru.microservice.user.domain.Email;
import com.backendguru.microservice.user.domain.Password;

public record NewUserRequest(String email, String password) {

}
