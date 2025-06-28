package com.Blog.BlogManagementSystem.Service;

import com.Blog.BlogManagementSystem.ModelDTO.UserDTO;
import com.Blog.BlogManagementSystem.ModelDTO.UserResponse;

import java.util.List;

public interface UserService {
    UserDTO getLoggedInUser(String email);
    UserDTO updateUser(String email, UserDTO userDTO);
    void deleteUser(String email);

    UserDTO getUserById(Long id);     // Admin only
    List<UserDTO> getAllUsers();      // Admin only
}

