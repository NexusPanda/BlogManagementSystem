package com.Blog.BlogManagementSystem.Repository;

import com.Blog.BlogManagementSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);  // login using email

    // If you also want to find by 'name'
    Optional<User> findByName(String name);
}
