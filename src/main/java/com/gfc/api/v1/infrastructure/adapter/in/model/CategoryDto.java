package com.gfc.api.v1.infrastructure.adapter.in.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.gfc.api.v1.domain.model.Category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Category Data Transfer Object")
public class CategoryDto {

    @Schema(description = "Category ID")
    private int id;

    @Schema(description = "Category Name")
    private String name;

    @Schema(description = "Category Description")
    private String description;

    
    /**
     * Convert List of Category to List of CategoryDto
     * @param bos List of Category
     * @return List of CategoryDto
     */
    public static List<CategoryDto> toDto(List<Category> bos) {
        List<CategoryDto> res = null;

        if (null != bos && !bos.isEmpty()) {
            res = new ArrayList<>();
            res = bos.stream().map(CategoryDto::toDto).collect(Collectors.toList());
        }

        return res;
    }

    /**
     * Convert Category to CategoryDto
     * @param bo Category
     * @return CategoryDto
     */
    public static CategoryDto toDto(Category bo) {

        CategoryDto dto = null;

        if (bo != null) {
            dto = new CategoryDto(bo.getId(), bo.getName(), bo.getDescription());
        }
        return dto;
    }
    
    public CategoryDto() {
    }

    public CategoryDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
