package com.akshay.blog.services;

import com.akshay.blog.entities.Post;
import com.akshay.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    Post updatePost(PostDto postDto, Integer postId);
    Post getPostById(Integer postId);
    List<Post> getAllPosts();
    void deletePost(Integer postId);
    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> searchPost(String name);
}
