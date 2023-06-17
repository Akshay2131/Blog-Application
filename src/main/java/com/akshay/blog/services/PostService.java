package com.akshay.blog.services;

import com.akshay.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    PostDto getPostById(Integer postId);
    List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);
    void deletePost(Integer postId);
    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> searchPost(String name);
}
