package com.Blog.BlogManagementSystem.Controller;

import com.Blog.BlogManagementSystem.ModelDTO.PostDTO;
import com.Blog.BlogManagementSystem.ModelDTO.PostResponse;
import com.Blog.BlogManagementSystem.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/admin/posts")
    public ResponseEntity<PostResponse> viewPost(){
        PostResponse postResponse = postService.viewPost();
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PostMapping("/public/author")
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO){
        PostDTO postDTO1 = postService.addPost(postDTO);
        return new ResponseEntity<>(postDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/admin/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,
                                              @PathVariable Long postId){
        PostDTO postDTO1 = postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(postDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/admin/posts/{postId}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable Long postId){
        PostDTO postDTO = postService.deletePost(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @GetMapping("/admin/posts/author/{authorId}")
    public ResponseEntity<PostResponse> viewAuthorById(@PathVariable Long authorId){
        PostResponse postResponse = postService.viewPostByAuthorId(authorId);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/admin/posts/category/{categoryId}")
    public ResponseEntity<PostResponse> viewPostByCategory(@PathVariable Long categoryId){
        PostResponse postResponse = postService.viewPostByCategoryId(categoryId);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/public/posts")
    public ResponseEntity<PostResponse> getAllPublishedPosts() {
        PostResponse postResponse = postService.getAllPublishedPosts();
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/public/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PatchMapping("/author/posts/{postId}/publish")
    public ResponseEntity<PostDTO> togglePublishStatus(@PathVariable Long postId) {
        PostDTO postDTO = postService.togglePublishStatus(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @GetMapping("/public/posts/tag/{tagId}")
    public ResponseEntity<PostResponse> getPostsByTag(@PathVariable Long tagId) {
        PostResponse postResponse = postService.viewPostByTagId(tagId);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
}
