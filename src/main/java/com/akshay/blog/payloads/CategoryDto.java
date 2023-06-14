package com.akshay.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private int categoryId;
    @NotEmpty
    @Size(min = 4, message = "Title must be minimum of 4 characters")
    private String categoryTitle;
    @NotEmpty
    @Size(min = 4, max = 20, message = "Description must be of between 4-20 characters")
    private String categoryDescription;
}
