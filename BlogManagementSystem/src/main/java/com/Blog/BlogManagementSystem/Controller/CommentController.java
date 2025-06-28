package com.Blog.BlogManagementSystem.Controller;

import com.Blog.BlogManagementSystem.ModelDTO.CommentDTO;
import com.Blog.BlogManagementSystem.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Public
    @GetMapping("/public/posts/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> viewComments(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.viewComments(postId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/public/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> addComments(@PathVariable Long postId,
                                                  @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.addComments(postId, commentDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/admin/comments/{id}/approve")
    public ResponseEntity<CommentDTO> approveComment(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.approveComment(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/comments/{id}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.deleteComment(id), HttpStatus.OK);
    }
}
