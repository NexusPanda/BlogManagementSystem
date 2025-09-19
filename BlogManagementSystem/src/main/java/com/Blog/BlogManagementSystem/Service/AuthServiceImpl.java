package com.Blog.BlogManagementSystem.Service;

import com.Blog.BlogManagementSystem.Model.User;
import com.Blog.BlogManagementSystem.Security.Request.LoginRequest;
import com.Blog.BlogManagementSystem.Security.Request.RegisterRequest;
import com.Blog.BlogManagementSystem.Repository.UserRepository;
import com.Blog.BlogManagementSystem.Security.Jwt.JwtUtils;
import com.Blog.BlogManagementSystem.Security.Response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public String registerUser(RegisterRequest dto) {
        // ✅ Correct Optional handling
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        String role = dto.getRole();
        if ("ADMIN".equalsIgnoreCase(role)) {
            user.setRole("ROLE_ADMIN"); // ✅ better to prefix with ROLE_
        } else {
            user.setRole("ROLE_USER");
        }

        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public ResponseEntity<?> loginUser(LoginRequest dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtUtils.getTokenFromUserName(userDetails);

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found after login"));

        return ResponseEntity.ok(new LoginResponse(
                token,
                user.getName(),
                userDetails.getAuthorities()
                        .stream()
                        .map(item -> item.getAuthority())
                        .toList()
        ));
    }
}
