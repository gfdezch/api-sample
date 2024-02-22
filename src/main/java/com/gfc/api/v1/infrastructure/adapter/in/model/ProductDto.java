package com.gfc.api.v1.infrastructure.adapter.in.model;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedSet;
import java.util.stream.Collectors;

import com.gfc.api.v1.domain.model.Product;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product Data Transfer Object")
public class ProductDto {

    @Schema(description = "Product id")
    private Long id;

    @Schema(description = "Product Name")
    private String name;

    @Schema(description = "Product Description")
    private String description;

    @Schema(description = "Metric order")
    private int order;
    
    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, int order) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public static List<ProductDto> toDto(SequencedSet<Product> bos) {
        List<ProductDto> res = null;

        if (null != bos && !bos.isEmpty()) {
            res = new ArrayList<ProductDto>();
            int order = 0;
            for (Product bo : bos) {
                res.add(toDto(bo, order++));
            }
        }
        
        return res;
    }

    public static List<ProductDto> toDto(List<Product> bos) {
        List<ProductDto> res = null;

        if (null != bos && !bos.isEmpty()) {
            res = bos.stream().map(bo -> toDto(bo, 0)).collect(Collectors.toList());
        }
        
        return res;
    }

    public static ProductDto toDto(Product bo, int order) {

        ProductDto res = null;

        if (null != bo) {
            res = new ProductDto(bo.getId(), bo.getName(), bo.getDescription(), order);
        }

        return res;
    }

    @Override
    public String toString() {
        return "ProductDto [description=" + description + ", id=" + id + ", name=" + name + ", order=" + order + "]";
    }
}