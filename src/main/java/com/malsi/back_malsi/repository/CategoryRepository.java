package com.malsi.back_malsi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malsi.back_malsi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    public Optional<Category> findByLabel(String label);
}
