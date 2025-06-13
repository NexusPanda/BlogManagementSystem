package com.Blog.BlogManagementSystem.Service;

import com.Blog.BlogManagementSystem.ModelDTO.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> viewComments(Long postId);

    CommentDTO addComments(Long postId, CommentDTO commentDTO);

    CommentDTO approveComment(Long id);

    CommentDTO deleteComment(Long id);
}
