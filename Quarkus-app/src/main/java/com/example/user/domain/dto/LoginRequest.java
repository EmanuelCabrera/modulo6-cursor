package com.example.user.domain.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    
    @NotBlank(message = "Username or email is required")
    public String usernameOrEmail;
    
    @NotBlank(message = "Password is required")
    public String password;
    
    public LoginRequest() {}
    
    public LoginRequest(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }
} 