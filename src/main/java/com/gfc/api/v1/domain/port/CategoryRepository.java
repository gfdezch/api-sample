package com.gfc.api.v1.domain.port;

import java.util.List;

import com.gfc.api.v1.domain.model.Category;

/**
 * The CategoryRepository class is responsible for handling the persistence of categories.
 * 
 */
public interface CategoryRepository {

    /**
     * Retrieve all categories
     * @return A list of all categories
     */
    List<Category> findAll();

    /**
     * Find a category by its ID
     * @param id The id of the category to find
     * @return The category with the given id, or null if no such category exists
     */
    Category findById(int id);

    /**
     * Check if a category exists by its ID
     * @param id The id of the category to check
     * @return true if the category exists, false otherwise
     */
    boolean existsById(int id);
}
