package com.gfc.api.v1.application;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.SequencedMap;
import java.util.SequencedSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfc.api.v1.domain.model.Category;
import com.gfc.api.v1.domain.model.Product;
import com.gfc.api.v1.domain.service.CategoryUseCase;
import com.gfc.api.v1.domain.service.MetricUseCase;
import com.gfc.api.v1.domain.service.ProductUseCase;

@Service
public class ProductService implements ProductUseCase {

    private static final Category category = new Category(1, "Camisetas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit Camisetas");

    private static final List<Product> products = List.of(
        new Product(1L, "V-NECH BASIC SHIRT", "Lorem ipsum dolor sit amet, V-NECH", category)
        , new Product(2L, "CONTRASTING FABRIC T-SHIRT", "Lorem ipsum dolor sit amet, CONTRASTING", category)
        , new Product(3L, "RAISED PAINT T-SHIRT", "Lorem ipsum dolor sit amet, RAISED", category)
        , new Product(4L, "PLEATED T-SHIRT", "Lorem ipsum dolor sit amet, PLEATED", category)
        , new Product(5L, "CONTRASTING LACE T-SHIRT", "Lorem ipsum dolor sit amet, V-CONTRASTING LACE", category)
        , new Product(6L, "SLOGAN T-SHIRT", "Lorem ipsum dolor sit amet, V-SLOGAN", category)
    );

    @Autowired
    private CategoryUseCase categoryService;

    @Autowired
    private MetricUseCase metricService;

    @Override
    public List<Product> findAll() {
        // TODO this is a mock implementation just for demonstration purposes
        return products;
    }

    @Override
    public Product findById(long id) {
        // TODO this is a mock implementation just for demonstration purposes
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public SequencedSet<Product> sort(int categoryId, int metricId, int top) throws Exception {
        SequencedSet<Product> res = null;

        if (!categoryService.IsValid(categoryId)) {
            throw new Exception("Invalid category");
        } 

        if (!metricService.IsValid(metricId)) {
            throw new Exception("Invalid metric");
        }
        
        SequencedSet<Long> productIdsOrderByMetric = this.metricService.getSorter(metricId).sortProductsBy(categoryId, top);

        if (null != productIdsOrderByMetric) {

            res = new LinkedHashSet<Product>();

            for (Long productId : productIdsOrderByMetric) {
                res.add(this.findById(productId));
            }
        }

        return res;
    }

    @Override
    public SequencedSet<Product> sort(int categoryId, Map<Long, Integer> weightedMetrics, int top) throws Exception {

        SequencedSet<Product> res = null;

        if (null == weightedMetrics || weightedMetrics.isEmpty()) {
            throw new UnsupportedOperationException("Invalid weighted metrics");
        }

        // validate all metrics in the map
        weightedMetrics.keySet().stream().forEach(metricId -> {
            if (!metricService.IsValid(metricId)) {
                throw new UnsupportedOperationException("Invalid metric");
            }
        });

        // Validate that the sum of the weights is 100
        int sum = weightedMetrics.values().stream().mapToInt(Integer::intValue).sum();
        if (sum != 100) {
            throw new UnsupportedOperationException("The sum of the weights must be 100");
        }

        // Get the products ordered by the weighted metrics storing the result in a map with the metric id as the key    
        Map<Long, SequencedSet<Long>> productIdsOrderByMetrics = weightedMetrics.keySet().stream().collect(Collectors.toMap(metricId -> metricId, metricId -> this.metricService.getSorter(metricId).sortProductsBy(categoryId, top)));

        // Let's store the scoring in a map with the product id as the key and the scoring as the value
        SequencedMap<Long, Integer> productIdsScoring = new LinkedHashMap<>();

        for (Long metricId : productIdsOrderByMetrics.keySet()) {

            SequencedSet<Long> productIdsOrderByMetric = productIdsOrderByMetrics.get(metricId);
            int weight = weightedMetrics.get(metricId);
            int position = top;
        
            for (Long productId : productIdsOrderByMetric) {
                int score = position * weight;

                if (productIdsScoring.containsKey(productId)) {
                    score += productIdsScoring.get(productId);
                }

                productIdsScoring.put(productId, score);
                position--;
            }
        }

        // Let's return the products ordered by the scoring
        res = productIdsScoring.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .map(e -> this.findById(e.getKey()))
            .limit(top)
            .collect(Collectors.toCollection(LinkedHashSet::new));
        
        return res;
    }
}
