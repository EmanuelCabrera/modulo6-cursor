package com.example.user.infrastructure.security;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import java.security.Principal;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthenticationFilter implements ContainerRequestFilter {
    
    @Inject
    JwtService jwtService;
    
    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();
        String fullPath = requestContext.getUriInfo().getRequestUri().getPath();
        
        // Allow public access to auth endpoints - check multiple variations
        if (path.startsWith("api/auth") || path.startsWith("auth") || 
            fullPath.contains("/api/auth") || fullPath.contains("/auth")) {
            return; // Allow public access to auth endpoints
        }
        
        // Require authentication for all other endpoints
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Missing or invalid Authorization header")
                    .build());
            return;
        }
        
        String token = authHeader.substring("Bearer ".length());
        
        try {
            // Extract username from token
            String username = jwtService.extractUsername(token);
            
            if (username == null) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid token")
                        .build());
                return;
            }
            
            // Validate the token
            if (!jwtService.validateToken(token, username)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid or expired token")
                        .build());
                return;
            }
            
            // Set the security context
            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return () -> username;
                }
                
                @Override
                public boolean isUserInRole(String role) {
                    return "user".equals(role);
                }
                
                @Override
                public boolean isSecure() {
                    return true;
                }
                
                @Override
                public String getAuthenticationScheme() {
                    return "Bearer";
                }
            });
            
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid token")
                    .build());
        }
    }
} 