package com.akshay.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    @NotEmpty
    private Integer id;
    @NotEmpty
    private String content;
    private UserDto user;
}
