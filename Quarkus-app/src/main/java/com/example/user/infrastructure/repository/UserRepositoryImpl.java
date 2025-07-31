package com.example.user.infrastructure.repository;

import com.example.user.domain.entity.User;
import com.example.user.domain.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class UserRepositoryImpl implements UserRepository, PanacheRepository<User> {
    
    @Override
    public User save(User user) {
        persist(user);
        return user;
    }
    
    @Override
    public Optional<User> findUserById(Long id) {
        User user = findById(id);
        return Optional.ofNullable(user);
    }
    
    @Override
    public Optional<User> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
    
    @Override
    public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        return find("username = ?1 or email = ?1", usernameOrEmail).firstResultOptional();
    }
    
    @Override
    public boolean existsByUsername(String username) {
        return count("username", username) > 0;
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return count("email", email) > 0;
    }
} 