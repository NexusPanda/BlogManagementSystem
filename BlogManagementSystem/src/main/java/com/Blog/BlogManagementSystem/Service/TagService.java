package com.Blog.BlogManagementSystem.Service;

import com.Blog.BlogManagementSystem.ModelDTO.TagDTO;
import com.Blog.BlogManagementSystem.ModelDTO.PostDTO;

import java.util.List;

public interface TagService {
    TagDTO addTag(TagDTO tagDTO);
    TagDTO updateTag(Long id, TagDTO tagDTO);
    void deleteTag(Long id);
    List<TagDTO> getAllTags();
    List<PostDTO> getPostsByTag(Long tagId);
}
