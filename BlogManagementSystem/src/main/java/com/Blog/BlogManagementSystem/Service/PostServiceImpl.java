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

    @Override
    public PostResponse viewPostByAuthorId(Long authorId) {
        List<Post> post = postRepository.findByAuthorId(authorId);
        if(post.isEmpty()){
            throw new APIException("Author with Id " + authorId + " not found !!!");
        }
        List<PostDTO> postDTOS = post.stream()
                .map(author -> modelMapper.map(author, PostDTO.class)).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setPostDTOList(postDTOS);
        return postResponse;
    }

    @Override
    public PostResponse viewPostByCategoryId(Long categoryId) {
        List<Post> posts  = postRepository.findByCategoryId(categoryId);
        if(posts.isEmpty()){
            throw new APIException("Category Id " + categoryId + " not found !!!");
        }
        List<PostDTO> postDTOS = posts.stream()
                .map(category -> modelMapper.map(category, PostDTO.class)).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setPostDTOList(postDTOS);
        return postResponse;
    }

    @Override
    public PostResponse getAllPublishedPosts() {
        List<Post> posts = postRepository.findByPublishedTrue();
        List<PostDTO> dtos = posts.stream()
                .map(p -> modelMapper.map(p, PostDTO.class)).toList();
        return new PostResponse(dtos);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO togglePublishStatus(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        post.setPublished(!post.isPublished());
        Post updated = postRepository.save(post);
        return modelMapper.map(updated, PostDTO.class);
    }

    @Override
    public PostResponse viewPostByTagId(Long tagId) {
        List<Post> posts = postRepository.findByTags_Id(tagId);
        List<PostDTO> dtos = posts.stream()
                .map(p -> modelMapper.map(p, PostDTO.class)).toList();
        return new PostResponse(dtos);
    }

}
