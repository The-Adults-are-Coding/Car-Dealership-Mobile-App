package com.alissar.cardealershipapp.data.model;

public class AuthResponse {
    private String token; // Usually, the server sends a JWT token
    private String message;
    // Getters
    public String getToken() {
        return token;
    }
    public String getMessage() {
        return message;
    }
}
