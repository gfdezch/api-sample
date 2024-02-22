package com.gfc.api.v1.domain.service;

import java.util.SequencedSet;

public interface SortUserCase {

    /**
     * Sort products by any matric in descending order
     * @param categoryId The category id to filter the products
     * @param top The number of products to return
     * @return A set of product IDs sorted by sales units in descending order
     */
    public SequencedSet<Long> sortProductsBy(int categoryId, int top);
}
