package com.Blog.BlogManagementSystem.Config;

import com.Blog.BlogManagementSystem.Model.User;
import com.Blog.BlogManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // ✅ Check if admin already exists using Optional
        if (userRepository.findByEmail("admin@blog.com").isEmpty()) {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@blog.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN"); // better for Spring Security
            userRepository.save(admin);
            System.out.println("✅ Admin user created.");
        } else {
            System.out.println("ℹ️ Admin user already exists.");
        }
    }
}
