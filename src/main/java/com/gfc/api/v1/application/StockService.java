package com.gfc.api.v1.application;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.SequencedSet;
import org.springframework.stereotype.Service;

import com.gfc.api.v1.domain.model.Stock;
import com.gfc.api.v1.domain.service.StockUseCase;

@Service
public class StockService implements StockUseCase {

    
    /**
     * This is a mock implementation of the stock repository
     * This map represents the relationship between a product and its stock
     */
    private static final Map<Long, Stock> productStockMaps = Map.of(
        1L, new Stock(0,9,4)
        , 2L, new Stock(9,9,35)
        , 3L, new Stock(20,2,20)
        , 4L, new Stock(10,30,25)
        , 5L, new Stock(0,1,0)
        , 6L, new Stock(5,2,9)
    );

    @Override
    public Stock findByProductId(Long productId) {
        // TODO this is a mock implementation just for demonstration purposes
        return productStockMaps.get(productId);
    }

    @Override
    public SequencedSet<Long> sortProductsBy(int categoryId, int top) {
        
        // TODO this is a mock implementation just for demonstration purposes
        SequencedSet<Long> res = new LinkedHashSet<Long>();

         productStockMaps.entrySet().stream()
         .sorted((e1, e2) -> e2.getValue().getTotal() - e1.getValue().getTotal())
         .limit(top).forEach(e -> res.add(e.getKey()));

         return res;
    }
}
