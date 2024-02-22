package com.gfc.api.v1.domain.service;

import java.util.List;

import com.gfc.api.v1.domain.model.Metric;

/**
 * The MetricUseCase class is responsible for handling the business logic related to metrics.
 */
public interface MetricUseCase {

    /**
     * Retrieve all metrics
     * @return A list of all metrics
     */
    List<Metric> findAll();

    /**
     * Find a metric by its ID
     * @param id The ID of the metric to find
     * @return The metric with the given ID, or null if no such metric exists
     */
    Metric findById(long id);

    /**
     * Check if a metric is valid
     * @param metricId The ID of the metric to check
     * @return true if the metric is valid, false otherwise
     */
    boolean IsValid(long metricId);

    /**
     * Retrieve all sorters, which are used to sort products by a given metric     * 
     * @return A list of all sorters
     */
    List<SortUserCase> getSorters();

    /**
     * Retrieve a sorter by its metric ID
     * @param metricId The ID of the metric to retrieve the sorter for
     * @return The sorter for the given metric ID, or null if no such sorter exists
     */    
    SortUserCase getSorter(long metricId);
}
