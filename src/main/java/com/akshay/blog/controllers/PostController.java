package com.akshay.blog.controllers;

import com.akshay.blog.payloads.ApiResponse;
import com.akshay.blog.payloads.PostDto;
import com.akshay.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable("userId") Integer uId,
            @PathVariable("categoryId") Integer cId) {
        PostDto createdPostDto = this.postService.createPost(postDto, uId, cId);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> postDtos= this.postService.getPostsByUser(userId);
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsBycategory(@PathVariable Integer categoryId) {
        List<PostDto> postDtos= this.postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value= "pageSize", defaultValue = "5", required = false) Integer pageSize
    ) {
        return ResponseEntity.ok(this.postService.getAllPosts(pageNumber, pageSize));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable("postId") Integer pid) {
        return ResponseEntity.ok(this.postService.getPostById(pid));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer pid) {
        this.postService.deletePost(pid);
        return ResponseEntity.ok(new ApiResponse("Post deleted successfully", true));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer pid) {
        PostDto updatedPostDto = this.postService.updatePost(postDto, pid);
        return ResponseEntity.ok(updatedPostDto);
    }

    @GetMapping("/posts/{name}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String name) {
        return ResponseEntity.ok(this.postService.searchPost(name));
    }
}
