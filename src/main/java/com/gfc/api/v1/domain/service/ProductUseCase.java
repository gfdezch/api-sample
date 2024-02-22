package com.gfc.api.v1.domain.service;

import java.util.List;
import java.util.Map;
import java.util.SequencedSet;

import com.gfc.api.v1.domain.model.Product;

/**
 * The ProductUseCase class is responsible for handling the business logic related to products.
 */
public interface ProductUseCase {

    /**
     * Retrieve all products
     * @return A list of all products
     */
    List<Product> findAll();

    /**
     * Retrieve a product by its ID
     * @param id The ID of the product to find
     * @return The product with the given ID, or null if no such product exists
     */
    Product findById(long id);

    /**
     * Sort products by any matric in descending order with a limit of top products.
     * @param categoryId The category id to filter the products
     * @param metricId The metric id to sort the products
     * @param top The max number of products to return
     * @return A set of product IDs sorted by the given metric in descending order
     * @throws Exception If the category or metric is invalid or any other error occurs.
     */
    SequencedSet<Product> sort(int categoryId, int metricId, int top) throws Exception;
    
    SequencedSet<Product> sort(int categoryId, Map<Long, Integer> weightedMetrics, int top) throws Exception;
}
