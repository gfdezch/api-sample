package com.gfc.api.v1.domain.model;

/**
 * The Stock class represents the stock of a product by size.
 */
public record Stock(int l, int m, int s) {

    public Stock {
        if (l < 0 || m < 0 || s < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
    }

    /**
     * Get the total stock
     * @return The total stock
     */
    public int getTotal() {
        return l + m + s;
    }
}
