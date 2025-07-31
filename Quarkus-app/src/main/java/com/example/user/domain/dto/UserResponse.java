package com.example.user.domain.dto;

import java.time.LocalDateTime;

public class UserResponse {
    public Long id;
    public String username;
    public String email;
    public String fullName;
    public boolean isActive;
    public LocalDateTime createdAt;
    
    public UserResponse() {}
    
    public UserResponse(Long id, String username, String email, String fullName, boolean isActive, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }
} 