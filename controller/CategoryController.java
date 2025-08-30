package com.example.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.Category;
import com.example.app.mapper.CategoryMapper;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {
	 private final CategoryMapper categorymapper;
	 
	 public CategoryController(CategoryMapper categoryMapper) {
	        this.categorymapper = categoryMapper;
	    }

	    @GetMapping
	    public List<Category> getCategories() {
	        return categorymapper.findAll();
	    }

}
