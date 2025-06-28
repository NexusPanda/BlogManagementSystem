package com.Blog.BlogManagementSystem.Service;

import com.Blog.BlogManagementSystem.Exception.APIException;
import com.Blog.BlogManagementSystem.Exception.ResourceNotFoundException;
import com.Blog.BlogManagementSystem.Model.Category;
import com.Blog.BlogManagementSystem.Model.Post;
import com.Blog.BlogManagementSystem.Model.Tag;
import com.Blog.BlogManagementSystem.Model.User;
import com.Blog.BlogManagementSystem.ModelDTO.PostDTO;
import com.Blog.BlogManagementSystem.ModelDTO.PostResponse;
import com.Blog.BlogManagementSystem.Repository.CategoryRepository;
import com.Blog.BlogManagementSystem.Repository.PostRepository;
import com.Blog.BlogManagementSystem.Repository.TagRepository;
import com.Blog.BlogManagementSystem.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public PostResponse viewPost() {
        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty()) {
            throw new APIException("Post Not Found");
        }

        List<PostDTO> postDTOS = postList.stream()
                .map(this::convertToDTO)
                .toList();

        return new PostResponse(postDTOS);
    }

    @Override
    public PostDTO addPost(PostDTO postDTO, String email) {
        // Validate unique title
        if (postRepository.findByTitle(postDTO.getTitle()) != null) {
            throw new APIException("Post title already exists!");
        }

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setPublished(false); // Default to unpublished

        // ✅ Set Author
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        post.setAuthor(user);

        // ✅ Set Category
        if (postDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(postDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDTO.getCategoryId()));
            post.setCategory(category);
        }

        // ✅ Set Tags
        if (postDTO.getTagIds() != null && !postDTO.getTagIds().isEmpty()) {
            List<Tag> tags = postDTO.getTagIds().stream()
                    .map(tagId -> tagRepository.findById(tagId)
                            .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId)))
                    .toList();
            post.setTags(tags);
        }

        post.setCreatedAt(LocalDateTime.now());

        Post saved = postRepository.save(post);
        return convertToDTO(saved);
    }


    @Override
    public PostDTO updatePost(PostDTO postDTO, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

        Post savedPost = modelMapper.map(postDTO, Post.class);
        savedPost.setId(postId); // retain original ID
        post = postRepository.save(savedPost);
        return convertToDTO(post);
    }

    @Override
    public PostDTO deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

        postRepository.delete(post);
        return convertToDTO(post);
    }

    @Override
    public PostResponse viewPostByAuthorId(Long authorId) {
        List<Post> posts = postRepository.findByAuthorId(authorId);
        if (posts.isEmpty()) {
            throw new APIException("Author with Id " + authorId + " not found !!!");
        }

        List<PostDTO> postDTOS = posts.stream()
                .map(this::convertToDTO)
                .toList();

        return new PostResponse(postDTOS);
    }

    @Override
    public PostResponse viewPostByCategoryId(Long categoryId) {
        List<Post> posts = postRepository.findByCategoryId(categoryId);
        if (posts.isEmpty()) {
            throw new APIException("Category Id " + categoryId + " not found !!!");
        }

        List<PostDTO> postDTOS = posts.stream()
                .map(this::convertToDTO)
                .toList();

        return new PostResponse(postDTOS);
    }

    @Override
    public PostResponse getAllPublishedPosts() {
        List<Post> posts = postRepository.findByPublishedTrue();
        List<PostDTO> dtos = posts.stream()
                .map(this::convertToDTO)
                .toList();

        return new PostResponse(dtos);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        return convertToDTO(post);
    }

    @Override
    public PostDTO togglePublishStatus(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        post.setPublished(!post.isPublished());
        Post updated = postRepository.save(post);

        return convertToDTO(updated);
    }

    @Override
    public PostResponse viewPostByTagId(Long tagId) {
        List<Post> posts = postRepository.findByTags_Id(tagId);

        List<PostDTO> dtos = posts.stream()
                .map(this::convertToDTO)
                .toList();

        return new PostResponse(dtos);
    }

    // ✅ CONVERSION: Fixes tagIds and authorName issue
    private PostDTO convertToDTO(Post post) {
        PostDTO dto = modelMapper.map(post, PostDTO.class);

        // Handle tag IDs
        if (post.getTags() != null) {
            dto.setTagIds(post.getTags().stream()
                    .map(tag -> tag.getId())
                    .toList());
        }

        // Handle author name
        if (post.getAuthor() != null) {
            dto.setAuthorName(post.getAuthor().getName());
        }

        // Handle category
        if (post.getCategory() != null) {
            dto.setCategoryId(post.getCategory().getId());
        }

        return dto;
    }
}
