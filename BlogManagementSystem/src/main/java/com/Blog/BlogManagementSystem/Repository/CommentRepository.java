package com.Blog.BlogManagementSystem.Repository;

import com.Blog.BlogManagementSystem.Model.Comment;
import com.Blog.BlogManagementSystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
