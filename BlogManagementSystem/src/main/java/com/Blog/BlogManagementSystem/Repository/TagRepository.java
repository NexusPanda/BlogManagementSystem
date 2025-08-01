package com.Blog.BlogManagementSystem.Repository;

import com.Blog.BlogManagementSystem.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
