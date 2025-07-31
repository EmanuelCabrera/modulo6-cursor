package com.example.user.infrastructure.controller;

import com.example.user.domain.dto.AuthResponse;
import com.example.user.domain.dto.LoginRequest;
import com.example.user.domain.dto.UserRegistrationRequest;
import com.example.user.domain.service.AuthService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {
    
    @Inject
    AuthService authService;
    
    @POST
    @Path("/register")
    public Response register(@Valid UserRegistrationRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return Response.status(Response.Status.CREATED)
                    .entity(response)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }
    
    @POST
    @Path("/login")
    public Response login(@Valid LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }
    
    public static class ErrorResponse {
        public String message;
        
        public ErrorResponse() {}
        
        public ErrorResponse(String message) {
            this.message = message;
        }
    }
} 