package com.Blog.BlogManagementSystem.Security.Request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;

    private String role;
}
