package com.Blog.BlogManagementSystem.Controller;

import com.Blog.BlogManagementSystem.ModelDTO.CommentDTO;
import com.Blog.BlogManagementSystem.ModelDTO.PostDTO;
import com.Blog.BlogManagementSystem.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/public/posts/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> viewComments(@PathVariable Long postId){
        List<CommentDTO> commentDTO = commentService.viewComments(postId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PostMapping("/public/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> addComments(@PathVariable Long postId,
                                                  @RequestBody CommentDTO commentDTO){
        CommentDTO commentDTO1 = commentService.addComments(postId,commentDTO);
        return new ResponseEntity<>(commentDTO1, HttpStatus.OK);
    }

    @PatchMapping("/admin/comments/{id}/approve")
    public ResponseEntity<CommentDTO> approveComment(@PathVariable Long id){
        CommentDTO commentDTO = commentService.approveComment(id);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/comments/{id}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable Long id){
        CommentDTO commentDTO = commentService.deleteComment(id);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }
}
