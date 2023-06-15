package com.akshay.blog.repositories;

import com.akshay.blog.entities.Category;
import com.akshay.blog.entities.Post;
import com.akshay.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}