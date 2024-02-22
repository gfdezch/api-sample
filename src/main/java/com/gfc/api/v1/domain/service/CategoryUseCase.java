package com.gfc.api.v1.domain.service;

import com.gfc.api.v1.domain.model.Category;

import java.util.List;

/**
 * The CategoryUseCase class is responsible for handling the business logic related to categories.
 * This may include operations such as retrieving categories.
 * 
 * Each method in this class should correspond to a specific use case for a category in the application.
 * For example, there might be a method for retrieving all categories, another method for retrieving a specific category, etc.
 *
 * This class should interact with the CategoryRepository to perform these operations.
 */
public interface CategoryUseCase {

    /**
     * Find a category by its ID
     * @param id The ID of the category to find
     * @return The category with the given ID, or null if no such category exists
     */
    public Category findById(int id);

    /**
     * Retrieve all categories
     * @return A list of all categories
     */
    public List<Category> findAll();

    /**
     * Check if a category is valid
     * @param categoryId The ID of the category to check
     * @return true if the category is valid, false otherwise
     */
    public boolean IsValid(int categoryId);
}
