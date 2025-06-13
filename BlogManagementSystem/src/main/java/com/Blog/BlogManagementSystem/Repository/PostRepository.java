package com.Blog.BlogManagementSystem.Repository;

import com.Blog.BlogManagementSystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);
    List<Post> findByAuthorId(Long authorId);

    List<Post> findByCategoryId(Long categoryId);

    List<Post> findByPublishedTrue();

    List<Post> findByTags_Id(Long tagId);
}
