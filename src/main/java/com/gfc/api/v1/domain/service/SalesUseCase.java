package com.gfc.api.v1.domain.service;


/**
 * The SalesUseCase class is responsible for handling the business logic related to sales.
 */
public interface SalesUseCase extends SortUserCase{

    /**
     * Find sales units by product id
     * @param productId The id of the product to find
     * @return The sales units with the given product ID, or null if no such sales units exists
     */
    public int findByProductId(Long productId);

}
