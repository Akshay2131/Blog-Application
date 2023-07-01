package com.akshay.blog.controllers;

import com.akshay.blog.payloads.ApiResponse;
import com.akshay.blog.payloads.CommentDto;
import com.akshay.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable("userId") Integer uId) {
        return new ResponseEntity<>(this.commentService.createComment(commentDto, uId), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(this.commentService.getCommentsByUser(userId));
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(this.commentService.getCommentsByPost(postId));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value= "pageSize", defaultValue = "5", required = false) Integer pageSize
    ) {
        return ResponseEntity.ok(this.commentService.getAllComments(pageNumber, pageSize));
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> getSingleComment(@PathVariable("commentId") Integer cid) {
        return ResponseEntity.ok(this.commentService.getCommentById(cid));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer cid) {
        this.commentService.deleteComment(cid);
        return ResponseEntity.ok(new ApiResponse("comment deleted successfully", true));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable("commentId") Integer cid) {
        return ResponseEntity.ok(this.commentService.updateComment(commentDto, cid));
    }
}
