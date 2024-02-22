package com.gfc.api.v1.infrastructure.adapter.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfc.api.v1.domain.service.CategoryUseCase;
import com.gfc.api.v1.infrastructure.adapter.in.model.CategoryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryUseCase categoryService;

    @Operation(operationId = "findAll", responses = {
        @ApiResponse(responseCode = "200", description = "Get all categories", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class), examples = {
                        @ExampleObject(value = "{}") })
        }),
        @ApiResponse(responseCode = "401", description = "Unauthorized request"),
        @ApiResponse(responseCode = "403", description = "Forbidden request"),
        @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping( value = "/all", produces = "application/json")
    public ResponseEntity<List<CategoryDto>> findAll() {
        ResponseEntity<List<CategoryDto>> res = ResponseEntity.noContent().build();

        List<CategoryDto> categories =  CategoryDto.toDto(categoryService.findAll());

        if (null != categories && !categories.isEmpty()) {
            res = ResponseEntity.ok(categories);
        }

        return res;
    }    

    @Operation(operationId = "findById", responses = {
        @ApiResponse(responseCode = "200", description = "Get a category by its ID", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class), examples = {
                        @ExampleObject(value = "{}") })
        }),
        @ApiResponse(responseCode = "401", description = "Unauthorized request"),
        @ApiResponse(responseCode = "403", description = "Forbidden request"),
        @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping( value = "/{id}", produces = "application/json")
    public ResponseEntity<CategoryDto> findById(@PathVariable int id) {
        ResponseEntity<CategoryDto> res = ResponseEntity.notFound().build();

        CategoryDto category = CategoryDto.toDto(categoryService.findById(id));

        if (null != category) {
            res = ResponseEntity.ok(category);
        }

        return res;
    }
}
