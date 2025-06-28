package com.Blog.BlogManagementSystem.Service;

import com.Blog.BlogManagementSystem.Security.Request.LoginRequest;
import com.Blog.BlogManagementSystem.Security.Request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    String registerUser(RegisterRequest registerDTO);
    ResponseEntity<?> loginUser(LoginRequest loginDTO);
}
