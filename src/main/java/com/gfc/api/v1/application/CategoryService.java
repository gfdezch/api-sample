package com.gfc.api.v1.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfc.api.v1.domain.model.Category;
import com.gfc.api.v1.domain.port.CategoryRepository;
import com.gfc.api.v1.domain.service.CategoryUseCase;

@Service
public class CategoryService implements CategoryUseCase {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean IsValid(int categoryId) {
        // TODO This is a mock implementation just for demonstration purposes
        return categoryRepository.existsById(categoryId);
    }
}
