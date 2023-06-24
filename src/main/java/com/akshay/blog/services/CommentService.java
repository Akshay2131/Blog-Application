package com.akshay.blog.services;

import com.akshay.blog.payloads.CommentDto;
import com.akshay.blog.payloads.PostDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer userId);
    CommentDto updateComment(CommentDto commentDto, Integer commentId);
    CommentDto getCommentById(Integer commentId);
    List<CommentDto> getAllComments(Integer pageNumber, Integer pageSize);
    void deleteComment(Integer commentId);
    List<CommentDto> getCommentsByUser(Integer userId);
}
