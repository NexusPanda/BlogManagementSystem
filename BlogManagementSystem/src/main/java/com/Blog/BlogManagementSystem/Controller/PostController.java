package com.Blog.BlogManagementSystem.Controller;

import com.Blog.BlogManagementSystem.ModelDTO.PostDTO;
import com.Blog.BlogManagementSystem.ModelDTO.PostResponse;
import com.Blog.BlogManagementSystem.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/posts")
    public ResponseEntity<PostResponse> viewPost() {
        return new ResponseEntity<>(postService.viewPost(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/public/author")
    public ResponseEntity<PostDTO> addPost(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestBody PostDTO postDTO) {
        PostDTO createdPost = postService.addPost(postDTO, userDetails.getUsername());
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Long postId) {
        return new ResponseEntity<>(postService.updatePost(postDTO, postId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/posts/{postId}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.deletePost(postId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/posts/author/{authorId}")
    public ResponseEntity<PostResponse> viewAuthorById(@PathVariable Long authorId) {
        return new ResponseEntity<>(postService.viewPostByAuthorId(authorId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/posts/category/{categoryId}")
    public ResponseEntity<PostResponse> viewPostByCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(postService.viewPostByCategoryId(categoryId), HttpStatus.OK);
    }

    // Public
    @GetMapping("/public/posts")
    public ResponseEntity<PostResponse> getAllPublishedPosts() {
        return new ResponseEntity<>(postService.getAllPublishedPosts(), HttpStatus.OK);
    }

    // Public
    @GetMapping("/public/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/author/posts/{postId}/publish")
    public ResponseEntity<PostDTO> togglePublishStatus(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.togglePublishStatus(postId), HttpStatus.OK);
    }

    // Public
    @GetMapping("/public/posts/tag/{tagId}")
    public ResponseEntity<PostResponse> getPostsByTag(@PathVariable Long tagId) {
        return new ResponseEntity<>(postService.viewPostByTagId(tagId), HttpStatus.OK);
    }
}
