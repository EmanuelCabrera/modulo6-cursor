package com.example.user.domain.service;

import com.example.user.domain.dto.AuthResponse;
import com.example.user.domain.dto.LoginRequest;
import com.example.user.domain.dto.UserRegistrationRequest;

public interface AuthService {
    AuthResponse register(UserRegistrationRequest request);
    AuthResponse login(LoginRequest request);
} 