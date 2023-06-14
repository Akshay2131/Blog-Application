package com.akshay.blog.controllers;

import com.akshay.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
}
