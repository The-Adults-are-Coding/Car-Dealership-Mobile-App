package com.alissar.cardealershipapp.data.model;

public class AuthResponse {
    private String token; // Usually, the server sends a JWT token
    private String message;

    private String id;
    // Getters
    public String getToken() {
        return token;
    }
    public String getId(){
        return id;
    }
    public String getMessage() {
        return message;
    }
}
