package com.gfc.api.v1.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gfc.api.v1.domain.model.Metric;
import com.gfc.api.v1.domain.service.MetricUseCase;
import com.gfc.api.v1.domain.service.SalesUseCase;
import com.gfc.api.v1.domain.service.SortUserCase;
import com.gfc.api.v1.domain.service.StockUseCase;

@Service
public class MetricService implements MetricUseCase {

    /**
     * Represents the enetity metrics
     * If we persist the metrics in a database, we should use a repository to access the data
     * For this example, we are using a mock implementation
     */
    private static final List<Metric> metrics = List.of(
        new Metric(1L, "SalesUnits", "Lorem ipsum dolor sit amet, Metric 1")
        , new Metric(2L, "StockSizeRatio", "Lorem ipsum dolor sit amet, Metric 2")
    );
    
    private Map<Long, SortUserCase> sorters;
    
    public MetricService(SalesUseCase salesService, StockUseCase stockService) {
        sorters = Map.of(
            1L, (categoryId, top) -> salesService.sortProductsBy(categoryId, top)
            , 2L, (categoryId, top) -> stockService.sortProductsBy(categoryId, top)
        );
    }

    @Override
    public List<Metric> findAll() {
        // TODO this is a mock implementation just for demonstration purposes
        return metrics;
    }

    @Override
    public Metric findById(long id) {
        // TODO this is a mock implementation just for demonstration purposes
        return metrics.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean IsValid(long metricId) {
        // TODO this is a mock implementation just for demonstration purposes
        return metrics.stream().anyMatch(m -> m.getId() == metricId);
    }

    @Override
    public List<SortUserCase> getSorters() {
        // TODO this is a mock implementation just for demonstration purposes
        return new ArrayList<>(sorters.values());
    }

    @Override
    public SortUserCase getSorter(long metricId) {
        return sorters.get(metricId);
    }
}
