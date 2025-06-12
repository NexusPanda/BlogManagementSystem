package com.Blog.BlogManagementSystem.Service;


import com.Blog.BlogManagementSystem.Exception.APIException;
import com.Blog.BlogManagementSystem.Exception.ResourceNotFoundException;
import com.Blog.BlogManagementSystem.Model.Post;
import com.Blog.BlogManagementSystem.ModelDTO.PostDTO;
import com.Blog.BlogManagementSystem.ModelDTO.PostResponse;
import com.Blog.BlogManagementSystem.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostResponse viewPost() {
        List<Post> postList = postRepository.findAll();
        if(postList.isEmpty()){
            throw new APIException("Post Not Found");
        }
        List<PostDTO> postDTOS = postList.stream()
                .map(post -> modelMapper.map(post, PostDTO.class)).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setPostDTOList(postDTOS);
        return postResponse;
    }

    @Override
    public PostDTO addPost(PostDTO postDTO) {
        Post post = postRepository.findByTitle(postDTO.getTitle());
        if(post != null){
            throw new APIException("Post Author " + postDTO.getAuthorName() + " is already exists !!!");
        }
        Post savedPost = modelMapper.map(postDTO, Post.class);
        postRepository.save(savedPost);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","PostId",postId));
        Post savedPost = modelMapper.map(postDTO, Post.class);
        savedPost.setId(postId);
        post = postRepository.save(savedPost);
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","PostId",postId));
        postRepository.delete(post);
        return modelMapper.map(post, PostDTO.class);
    }
}
