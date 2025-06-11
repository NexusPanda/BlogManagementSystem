package com.Blog.BlogManagementSystem.Service;

import com.Blog.BlogManagementSystem.ModelDTO.UserDTO;
import com.Blog.BlogManagementSystem.ModelDTO.UserResponse;

public interface UserService {

    UserResponse viewUser();
    UserDTO updateUser(UserDTO userDTO, Long userId);
    UserDTO deleteUser(Long userId);
}
