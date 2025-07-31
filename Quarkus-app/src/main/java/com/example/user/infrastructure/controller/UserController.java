package com.example.user.infrastructure.controller;

import com.example.user.domain.dto.UserResponse;
import com.example.user.domain.entity.User;
import com.example.user.domain.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    
    @Inject
    UserRepository userRepository;
    
    @GET
    public Response getAllUsers() {
        List<User> users = ((com.example.user.infrastructure.repository.UserRepositoryImpl) userRepository).listAll();
        List<UserResponse> userResponses = users.stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
        
        return Response.ok(userResponses).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        return userRepository.findUserById(id)
                .map(user -> Response.ok(mapToUserResponse(user)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("User not found"))
                        .build());
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
    
    public static class ErrorResponse {
        public String message;
        
        public ErrorResponse() {}
        
        public ErrorResponse(String message) {
            this.message = message;
        }
    }
} 