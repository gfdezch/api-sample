package com.gfc.api.v1.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.SequencedSet;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gfc.api.AbstractTest;
import com.gfc.api.v1.domain.model.Product;

public class ProductServiceTest extends AbstractTest {

    @Autowired
    private ProductService productService;

    @Test
    @Order(1)
    void contextLoads() throws Exception {
        assertThat(productService).isNotNull();
    }

    @Test
    @Order(2)
    void testFindAll() {
        List<Product> products = productService.findAll();
        assertNotNull(products);
        assertTrue(!products.isEmpty());
    }

    @Test
    @Order(3)
    void testFindById() {
        Product product = productService.findById(1);
        assertNotNull(product);
        assertEquals(1, product.getId());
    }

    @Test
    @Order(4)
    void testsortByMetric() {
        int categoryId = 1;
        int metricId = 1;
        int top = 5;
        try {
            SequencedSet<Product> products = this.productService.sort(categoryId, metricId, top);
            assertNotNull(products);
            assertTrue(!products.isEmpty());
            assertEquals(top, products.size());
            assertEquals(products.getFirst().getId(), 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    void testSortByMetric() {

        int categoryId = 1;
        Map<Long,Integer> weithedMetrics = new HashMap<>();
        weithedMetrics.put(1L, 80);
        weithedMetrics.put(2L, 20);        
        int top = 5;

        try {
            SequencedSet<Product> products = this.productService.sort(categoryId, weithedMetrics, top);
            assertNotNull(products);
            assertTrue(!products.isEmpty());
            assertEquals(top, products.size());
            assertEquals(products.getFirst().getId(), 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(6)
    void testSortByMetricInvalidCategory() {

        int categoryId = 0;
        Map<Long,Integer> weithedMetrics = new HashMap<>();
        weithedMetrics.put(1L, 80);
        weithedMetrics.put(2L, 20);        
        int top = 5;

        try {
            this.productService.sort(categoryId, weithedMetrics, top);
        } catch (Exception e) {
            assertEquals("Invalid category", e.getMessage());
        }
    }

    @Test
    @Order(7)
    void testSortByMetricInvalidMetric() {

        int categoryId = 1;
        Map<Long,Integer> weithedMetrics = new HashMap<>();
        weithedMetrics.put(0L, 80);
        weithedMetrics.put(2L, 20);        
        int top = 5;

        try {
            this.productService.sort(categoryId, weithedMetrics, top);
        } catch (Exception e) {
            assertEquals("Invalid metric", e.getMessage());
        }
    }

    @Test
    @Order(8)
    void testSortByMetricInvalidWeightedMetrics() {

        int categoryId = 1;
        Map<Long,Integer> weithedMetrics = new HashMap<>();
        int top = 5;

        try {
            this.productService.sort(categoryId, weithedMetrics, top);
        } catch (Exception e) {
            assertEquals("Invalid weighted metrics", e.getMessage());
        }
    }

    @Test
    @Order(9)
    void testSortByMetrics() {

        int categoryId = 1;
        Map<Long,Integer> weithedMetrics = new HashMap<>();
        weithedMetrics.put(1L, 99);
        weithedMetrics.put(2L, 1);        
        int top = 5;

        try {
            SequencedSet<Product> productsByMetrics = this.productService.sort(categoryId, weithedMetrics, top);
            assertNotNull(productsByMetrics);
            assertTrue(!productsByMetrics.isEmpty());
            assertEquals(top, productsByMetrics.size());
            
            SequencedSet<Product> productsByMetric = this.productService.sort(categoryId, 1, top);
            assertNotNull(productsByMetric);
            assertTrue(!productsByMetric.isEmpty());
            assertEquals(top, productsByMetric.size());
            assertEquals(productsByMetric.getFirst().getId(), 5);

            // let's compare the products by metrics and by metric
            assertEquals(productsByMetrics.getFirst().getId(), productsByMetric.getFirst().getId());



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
