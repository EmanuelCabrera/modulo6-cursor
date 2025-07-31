package com.example.user.application.service;

import com.example.user.domain.dto.AuthResponse;
import com.example.user.domain.dto.LoginRequest;
import com.example.user.domain.dto.UserRegistrationRequest;
import com.example.user.domain.dto.UserResponse;
import com.example.user.domain.entity.User;
import com.example.user.domain.repository.UserRepository;
import com.example.user.domain.service.AuthService;
import com.example.user.infrastructure.security.JwtService;
import com.example.user.infrastructure.security.PasswordService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    PasswordService passwordService;
    
    @Inject
    JwtService jwtService;
    
    @Override
    public AuthResponse register(UserRegistrationRequest request) {
        // Validar que el usuario no exista
        if (userRepository.existsByUsername(request.username)) {
            throw new BadRequestException("Username already exists");
        }
        
        if (userRepository.existsByEmail(request.email)) {
            throw new BadRequestException("Email already exists");
        }
        
        // Crear nuevo usuario
        User user = new User();
        user.username = request.username;
        user.email = request.email;
        user.password = passwordService.hashPassword(request.password);
        user.fullName = request.fullName;
        
        // Guardar usuario
        user = userRepository.save(user);
        
        // Generar token JWT
        String token = jwtService.generateToken(user);
        
        // Crear respuesta
        UserResponse userResponse = mapToUserResponse(user);
        
        return new AuthResponse(token, userResponse);
    }
    
    @Override
    public AuthResponse login(LoginRequest request) {
        // Buscar usuario por username o email
        User user = userRepository.findByUsernameOrEmail(request.usernameOrEmail)
                .orElseThrow(() -> new NotFoundException("User not found"));
        
        // Verificar contraseña
        if (!passwordService.verifyPassword(request.password, user.password)) {
            throw new BadRequestException("Invalid credentials");
        }
        
        // Verificar que el usuario esté activo
        if (!user.isActive) {
            throw new BadRequestException("User account is disabled");
        }
        
        // Generar token JWT
        String token = jwtService.generateToken(user);
        
        // Crear respuesta
        UserResponse userResponse = mapToUserResponse(user);
        
        return new AuthResponse(token, userResponse);
    }
    
    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
            user.id,
            user.username,
            user.email,
            user.fullName,
            user.isActive,
            user.createdAt
        );
    }
} 