package com.Blog.BlogManagementSystem.Controller;

import com.Blog.BlogManagementSystem.ModelDTO.UserDTO;
import com.Blog.BlogManagementSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/users/me")
    public ResponseEntity<UserDTO> getOwnProfile(@AuthenticationPrincipal UserDetails userDetails) {
        UserDTO userDTO = userService.getLoggedInUser(userDetails.getUsername());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/users/me")
    public ResponseEntity<UserDTO> updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                                                 @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userDetails.getUsername(), userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/users/me")
    public ResponseEntity<String> deleteAccount(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(userDetails.getUsername());
        return new ResponseEntity<>("User account deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
