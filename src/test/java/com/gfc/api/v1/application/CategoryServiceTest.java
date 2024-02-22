package com.gfc.api.v1.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gfc.api.AbstractTest;
import com.gfc.api.v1.domain.model.Category;
import com.gfc.api.v1.domain.service.CategoryUseCase;


/**
 * Represents the test class for the category service.
 * This is just a simple test class to demonstrate the use of the test containers and how the hexagonal architecture can be tested,
 * in this case at application layer.
 */
public class CategoryServiceTest extends AbstractTest {

    @Autowired
    private CategoryUseCase categoryService;

	@Test
    @Order(1)
	void contextLoads() throws Exception {
		assertThat(categoryService).isNotNull();
	}

    @Test
    @Order(2)
    void testFindAll() {
        List<Category> categories = categoryService.findAll();
        assertNotNull(categories);
        assertTrue(!categories.isEmpty());
    }

    @Test
    @Order(3)
    void testFindById() {
        Category category = categoryService.findById(1);
        assertNotNull(category);
        assertEquals(1, category.getId());
    }
}