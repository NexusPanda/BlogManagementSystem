package com.Blog.BlogManagementSystem.Repository;

import com.Blog.BlogManagementSystem.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
