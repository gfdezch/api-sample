package com.gfc.api.v1.application;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.SequencedSet;

import org.springframework.stereotype.Service;

import com.gfc.api.v1.domain.service.SalesUseCase;

@Service
public class SaleService implements SalesUseCase {

    /**
     * This is a mock implementation of the sales repository
     * This map represents the relationship between a product and its sales
     */
    private static Map<Long, Integer> productSalesMap = Map.of(
        1L, 100
        , 2L, 50
        , 3L, 80
        , 4L, 3
        , 5L, 650
        , 6L, 20
    );

    @Override
    public int findByProductId(Long productId) {
        // TODO this is a mock implementation just for demonstration purposes
        return productSalesMap.get(productId);
    }

    @Override
    public SequencedSet<Long> sortProductsBy(int categoryId, int top) {
                // TODO this is a mock implementation just for demonstration purposes
        SequencedSet<Long> res = new LinkedHashSet<Long>();

         productSalesMap.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .limit(top)
            .forEach(e -> res.add(e.getKey()));

         return res;
    }
}
