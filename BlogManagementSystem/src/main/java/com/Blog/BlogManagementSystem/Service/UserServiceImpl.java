package com.Blog.BlogManagementSystem.Service;


import com.Blog.BlogManagementSystem.Exception.APIException;
import com.Blog.BlogManagementSystem.Exception.ResourceNotFoundException;
import com.Blog.BlogManagementSystem.Model.User;
import com.Blog.BlogManagementSystem.ModelDTO.UserDTO;
import com.Blog.BlogManagementSystem.ModelDTO.UserResponse;
import com.Blog.BlogManagementSystem.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponse viewUser() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()){
            throw new APIException("No Users Found");
        }
        List<UserDTO> userDTOList = userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class)).toList();
        UserResponse userResponse = new UserResponse();
        userResponse.setUserDTOList(userDTOList);
        return userResponse;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long userId) {
        User updatedDTO = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","UserId",userId));
        User savedUser = modelMapper.map(userDTO, User.class);
        savedUser.setId(userId);
        updatedDTO = userRepository.save(savedUser);
        return modelMapper.map(updatedDTO, UserDTO.class);
    }

    @Override
    public UserDTO deleteUser(Long userId) {
        User dbUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","UserName",userId));
        userRepository.delete(dbUser);
        return modelMapper.map(dbUser, UserDTO.class);
    }
}
