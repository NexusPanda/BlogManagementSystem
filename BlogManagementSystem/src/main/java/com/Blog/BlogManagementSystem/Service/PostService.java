package com.Blog.BlogManagementSystem.Service;

import com.Blog.BlogManagementSystem.ModelDTO.PostDTO;
import com.Blog.BlogManagementSystem.ModelDTO.PostResponse;

public interface PostService {
    PostResponse viewPost();

    PostDTO addPost(PostDTO postDTO);

    PostDTO updatePost(PostDTO postDTO, Long postId);

    PostDTO deletePost(Long postId);
}
