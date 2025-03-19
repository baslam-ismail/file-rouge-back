package com.malsi.back_malsi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malsi.back_malsi.dto.CategoryDto;
import com.malsi.back_malsi.model.Category;
import com.malsi.back_malsi.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    
    public CategoryDto createCategory(Category category) {
        return this.modelMapper.map(this.categoryRepository.save(category), CategoryDto.class);
    }

    public CategoryDto getCategoryById(Integer id) {
        Category category = this.categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }

        return this.modelMapper.map(category, CategoryDto.class);
    }

    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories == null) {
            return null;
        }
        List<CategoryDto> categoriesDto = new ArrayList<>();

        for (Category category : categories) {
            categoriesDto.add(this.modelMapper.map(category, CategoryDto.class));
        }

        return categoriesDto;
    }

    public CategoryDto getCatgoryByLabel(String label) {
        Optional<Category> category = this.categoryRepository.findByLabel(label);
        if (category == null) {
            return null;
        }

        return this.modelMapper.map(category, CategoryDto.class);
    }

    public Boolean deleteCategory(Integer id) {
        Category category = this.categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return false;
        }

        this.categoryRepository.delete(category);
        return true;
    }
}
