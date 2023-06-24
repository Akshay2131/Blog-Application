package com.akshay.blog.services.impl;

import com.akshay.blog.entities.Comment;
import com.akshay.blog.entities.User;
import com.akshay.blog.exceptions.ResourceNotFoundException;
import com.akshay.blog.payloads.CommentDto;
import com.akshay.blog.repositories.CommentRepo;
import com.akshay.blog.repositories.UserRepo;
import com.akshay.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setUser(user);
        Comment createdComment = this.commentRepo.save(comment);
        return this.modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        comment.setContent(commentDto.getContent());
        Comment updatedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public CommentDto getCommentById(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        return this.modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllComments(Integer pageNumber, Integer pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Comment> pageComment = this.commentRepo.findAll(p);
        List<Comment> comments = pageComment.getContent();
        List<CommentDto> commentDtos = comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        this.commentRepo.delete(comment);
    }

    @Override
    public List<CommentDto> getCommentsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        List<Comment> comments = this.commentRepo.findByUser(user);
        List<CommentDto> commentDtos = comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }
}
