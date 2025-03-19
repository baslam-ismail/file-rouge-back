package com.malsi.back_malsi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malsi.back_malsi.dto.CategoryDto;
import com.malsi.back_malsi.model.Category;
import com.malsi.back_malsi.service.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody Category category) {
        CategoryDto createdCategory = categoryService.createCategory(category);
        
        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
        CategoryDto category = categoryService.getCategoryById(id);
        if (category == null) {
            return new ResponseEntity<CategoryDto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categories = categoryService.getCategories();
        if (categories == null) {
            return new ResponseEntity<List<CategoryDto>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<CategoryDto>>(categories, HttpStatus.OK);
    }

    @GetMapping("/label/{label}")
    public ResponseEntity<CategoryDto> getCategoryByLabel(@PathVariable String label) {
        CategoryDto category = categoryService.getCatgoryByLabel(label);
        if (category == null) {
            return new ResponseEntity<CategoryDto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
    }
    
}
