package com.gfc.api.v1.infrastructure.adapter.in;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import com.gfc.api.AbstractTest;
import com.gfc.api.v1.infrastructure.adapter.in.model.CategoryDto;

/**
 * Represents the test class for the category controller.
 * This is just a simple test class to demonstrate the use of the test containers and how the hexagonal architecture can be tested,
 * in this case at infrastructure layer.
 */
public class CategoryControllerTest extends AbstractTest {
    
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    @Order(1)
    public void contextLoads() throws Exception {
        assertNotNull(restTemplate);
    }


    public void testFindAll() {
        ResponseEntity<CategoryDto[]> categoriesResponse = restTemplate
                .getForEntity("http://localhost:" + port + "/api/v1/category/all", CategoryDto[].class);

        assertTrue(categoriesResponse.getStatusCode().is2xxSuccessful());
        assertNotNull(categoriesResponse);
        assertNotNull(categoriesResponse.getBody());
        CategoryDto[] categoryDtos = categoriesResponse.getBody();
        assertNotNull(categoryDtos);
        assertTrue(0 < categoryDtos.length);

    }
}
