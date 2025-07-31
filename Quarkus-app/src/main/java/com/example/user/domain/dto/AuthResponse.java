package com.example.user.domain.dto;

public class AuthResponse {
    public String token;
    public String type = "Bearer";
    public UserResponse user;
    
    public AuthResponse() {}
    
    public AuthResponse(String token, UserResponse user) {
        this.token = token;
        this.user = user;
    }
} 