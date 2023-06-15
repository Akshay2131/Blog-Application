package com.akshay.blog.services;

import com.akshay.blog.entities.Post;
import com.akshay.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    Post createPost(PostDto postDto);
    Post updatePost(PostDto postDto, Integer postId);
    Post getPostById(Integer postId);
    List<Post> getAllPosts();
    void deletePost(Integer postId);
    List<Post> getPostsByUser(Integer userId);
    List<Post> getPostsByCategory(Integer categoryId);
    List<Post> searchPost(String name);
}
