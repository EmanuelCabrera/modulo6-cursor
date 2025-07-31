package com.example.user.infrastructure.config;

import com.example.user.domain.entity.User;
import com.example.user.domain.repository.UserRepository;
import com.example.user.infrastructure.security.PasswordService;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DataInitializer {
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    PasswordService passwordService;
    
    @Transactional
    public void initializeData(@Observes StartupEvent startupEvent) {
        // Verificar si ya existe el usuario admin
        if (!userRepository.existsByUsername("admin")) {
            User adminUser = new User();
            adminUser.username = "admin";
            adminUser.email = "admin@example.com";
            adminUser.password = passwordService.hashPassword("password");
            adminUser.fullName = "Admin User";
            adminUser.isActive = true;
            
            userRepository.save(adminUser);
            System.out.println("Usuario admin creado: admin@example.com / password");
        }
    }
} 