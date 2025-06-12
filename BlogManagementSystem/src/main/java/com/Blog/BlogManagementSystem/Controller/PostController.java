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

    @PostMapping("/public/posts")
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
}
