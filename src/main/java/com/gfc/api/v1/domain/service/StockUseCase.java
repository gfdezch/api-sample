package com.gfc.api.v1.domain.service;

import com.gfc.api.v1.domain.model.Stock;

/**
 * The StockUseCase class is responsible for handling the business logic related to stock.
 */
public interface StockUseCase extends SortUserCase {

    /**
     * Find a stock by its product id
     * @param productId The id of the product to find
     * @return The stock with the given product ID, or null if no such stock exists
     */
    public Stock findByProductId(Long productId);
}
