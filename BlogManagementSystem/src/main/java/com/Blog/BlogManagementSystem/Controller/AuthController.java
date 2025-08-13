package com.Blog.BlogManagementSystem.Controller;

import com.Blog.BlogManagementSystem.Security.Jwt.JwtUtils;
import com.Blog.BlogManagementSystem.Security.Request.LoginRequest;
import com.Blog.BlogManagementSystem.Security.Request.RegisterRequest;
import com.Blog.BlogManagementSystem.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerDTO) {
        String response = authService.registerUser(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginDTO) {
        return new ResponseEntity<>(authService.loginUser(loginDTO), HttpStatus.OK);
    }
}
