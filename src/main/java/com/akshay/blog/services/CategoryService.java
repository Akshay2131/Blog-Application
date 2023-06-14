package com.akshay.blog.services;

import com.akshay.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    void deleteCategory(int categoryId);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(int categoryId);
    CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
}
