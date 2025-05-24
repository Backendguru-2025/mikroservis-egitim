package com.backendguru.microservice.user.domain;

import org.springframework.util.DigestUtils;

//ValueObject
public class Password {

    private final String hashedValue;

    public Password(String actualPassword) {
        if (actualPassword == null || actualPassword.length() < 8) {
            throw new IllegalArgumentException("Password must contain at least 8 characters");
        }
        this.hashedValue = hashPassword(actualPassword);
    }

    private String hashPassword(String actualPassword) {
        // BCrypt veya benzeri bir algoritma kullanarak şifreyi hashleyin
        return DigestUtils.md5DigestAsHex(actualPassword.getBytes());
    }

    public boolean verify(String inputPassword) {
        // BCrypt veya benzeri bir algoritma kullanarak şifreyi dogrulayin
        return DigestUtils.md5DigestAsHex(inputPassword.getBytes()).equals(this.hashedValue);
    }

    public String getHashedValue() {
        return hashedValue;
    }
}
