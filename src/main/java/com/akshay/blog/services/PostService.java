package com.akshay.blog.services;

import com.akshay.blog.payloads.PostDto;
import com.akshay.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    PostDto getPostById(Integer postId);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    void deletePost(Integer postId);
    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> searchPost(String name);
}
