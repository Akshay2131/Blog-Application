package com.akshay.blog.services.impl;

import com.akshay.blog.entities.Category;
import com.akshay.blog.entities.Post;
import com.akshay.blog.entities.User;
import com.akshay.blog.exceptions.ResourceNotFoundException;
import com.akshay.blog.payloads.PostDto;
import com.akshay.blog.payloads.PostResponse;
import com.akshay.blog.repositories.CategoryRepo;
import com.akshay.blog.repositories.PostRepo;
import com.akshay.blog.repositories.UserRepo;
import com.akshay.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " id ", categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createdPost = this.postRepo.save(post);
        return this.modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = null;
        sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " id ", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keywords) {
        List<Post> posts = this.postRepo.findByTitleContaining(keywords);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
