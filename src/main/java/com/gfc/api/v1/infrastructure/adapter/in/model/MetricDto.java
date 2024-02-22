package com.gfc.api.v1.infrastructure.adapter.in.model;

import java.util.List;
import java.util.stream.Collectors;

import com.gfc.api.v1.domain.model.Metric;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Metric Data Transfer Object")
public class MetricDto {

    @Schema(description = "Metric id")
    private Long id;

    @Schema(description = "Metric Name")
    private String name;

    @Schema(description = "Metric Description")
    private String description;
    
    public MetricDto() {
    }

    public MetricDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public static List<MetricDto> toDto(List<Metric> bos) {
        List<MetricDto> res = null;

        if (null != bos && !bos.isEmpty()) {
            res = bos.stream().map(MetricDto::toDto).collect(Collectors.toList());
        }
        
        return res;
    }

    public static MetricDto toDto(Metric bo) {

        MetricDto res = null;

        if (null != bo) {
            res = new MetricDto(bo.getId(), bo.getName(), bo.getDescription());
        }

        return res;
    }
}
