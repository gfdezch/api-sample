package com.gfc.api.v1.infrastructure.adapter.in;

import java.util.List;
import java.util.SequencedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gfc.api.v1.domain.model.Product;
import com.gfc.api.v1.domain.service.ProductUseCase;
import com.gfc.api.v1.infrastructure.adapter.in.model.ProductDto;
import com.gfc.api.v1.infrastructure.adapter.in.model.SortRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductUseCase  productService;

    @Operation(operationId = "findAll", responses = {
        @ApiResponse(responseCode = "200", description = "Get all products", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class), examples = {
                        @ExampleObject(value = "{}") })
        }),
        @ApiResponse(responseCode = "401", description = "Unauthorized request"),
        @ApiResponse(responseCode = "403", description = "Forbidden request"),
        @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping( value = "/all", produces = "application/json")
    public ResponseEntity<List<ProductDto>> findAll() {
        ResponseEntity<List<ProductDto>> res = ResponseEntity.noContent().build();

        List<ProductDto> products =  ProductDto.toDto(productService.findAll());

        if (null != products && !products.isEmpty()) {
            res = ResponseEntity.ok(products);
        }

        return res;
    }

    @Operation(operationId = "sort" , responses = {
        @ApiResponse(responseCode = "200", description = "Sort products by metric", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class), examples = {
                        @ExampleObject(value = "{}") })
        }),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized request"),
        @ApiResponse(responseCode = "403", description = "Forbidden request"),
        @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping( value = "/sort/{categoryId}/{metricId}", produces = "application/json")
    public ResponseEntity<List<ProductDto>> sortByMetric(@PathVariable int categoryId, @PathVariable int metricId, @RequestParam int top) {
        ResponseEntity<List<ProductDto>> res = ResponseEntity.noContent().build();

        if ( 0 > categoryId || 0 > metricId || 0 > top ) {
            return ResponseEntity.badRequest().build();
        }

        try {
            SequencedSet<Product> productsOrderByMetric = this.productService.sort(categoryId, metricId, top);

            if (null != productsOrderByMetric && !productsOrderByMetric.isEmpty()) {
                List<ProductDto> products = ProductDto.toDto(productsOrderByMetric);
                res = ResponseEntity.ok(products);
            }

        } catch (Exception e) {
            // TODO: set the error message in the response
            res = ResponseEntity.badRequest().build();
        }

        return res;
    }

    @PostMapping( value = "/sort/{categoryId}", produces = "application/json")
    public ResponseEntity<List<ProductDto>> sortByMetrics(@PathVariable int categoryId, @RequestBody SortRequest request) {
        ResponseEntity<List<ProductDto>> res = ResponseEntity.noContent().build();

        if (null == request || request.getWeightedMetrics().isEmpty() || 0 > request.getTop() ) {
            return ResponseEntity.badRequest().build();
        }

        try {

            SequencedSet<Product> productsOrderByMetric = this.productService.sort(categoryId, request.getWeightedMetrics(), request.getTop());

            if (null != productsOrderByMetric && !productsOrderByMetric.isEmpty()) {
                List<ProductDto> products = ProductDto.toDto(productsOrderByMetric);
                res = ResponseEntity.ok(products);
            }

        } catch (Exception e) {
            // TODO: set the error message in the response
            res = ResponseEntity.badRequest().build();
        }

        return res;
    }
}
