package com.Blog.BlogManagementSystem.Controller;


import com.Blog.BlogManagementSystem.ModelDTO.UserDTO;
import com.Blog.BlogManagementSystem.ModelDTO.UserResponse;
import com.Blog.BlogManagementSystem.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public ResponseEntity<UserResponse> viewUser() {
        UserResponse userResponse = userService.viewUser();
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,
                                              @PathVariable Long userId) {
        UserDTO savedUserDto = userService.updateUser(userDTO,userId);
        return new ResponseEntity<>(savedUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long userId) {
        UserDTO userDTO = userService.deleteUser(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}