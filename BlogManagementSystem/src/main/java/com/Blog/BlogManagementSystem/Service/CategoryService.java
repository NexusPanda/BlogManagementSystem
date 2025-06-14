package com.Blog.BlogManagementSystem.Service;

import com.Blog.BlogManagementSystem.ModelDTO.CategoryDTO;

import java.util.List;

public interface CategoryService {


    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);

    List<CategoryDTO> getAllCategories();
}
