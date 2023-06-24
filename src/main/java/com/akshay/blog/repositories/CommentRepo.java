package com.akshay.blog.repositories;

import com.akshay.blog.entities.Comment;
import com.akshay.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findByUser(User user);
}
