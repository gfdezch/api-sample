package com.gfc.api.v1.infrastructure.adapter.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfc.api.v1.domain.service.MetricUseCase;
import com.gfc.api.v1.infrastructure.adapter.in.model.MetricDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/v1/metric")
public class MetricController {

    @Autowired
    private MetricUseCase metricService;

    @Operation(operationId = "findAll", responses = {
        @ApiResponse(responseCode = "200", description = "Get all metrics", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MetricDto.class), examples = {
                        @ExampleObject(value = "{}") })
        }),
        @ApiResponse(responseCode = "401", description = "Unauthorized request"),
        @ApiResponse(responseCode = "403", description = "Forbidden request"),
        @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping( value = "/all", produces = "application/json")
    public ResponseEntity<List<MetricDto>> findAll() {
        ResponseEntity<List<MetricDto>> res = ResponseEntity.noContent().build();

        List<MetricDto> metrics =  MetricDto.toDto(metricService.findAll());

        if (null != metrics && !metrics.isEmpty()) {
            res = ResponseEntity.ok(metrics);
        }

        return res;
    }

    @Operation(operationId = "findById", responses = {
        @ApiResponse(responseCode = "200", description = "Get a metric by its ID", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MetricDto.class), examples = {
                        @ExampleObject(value = "{}") })
        }),
        @ApiResponse(responseCode = "401", description = "Unauthorized request"),
        @ApiResponse(responseCode = "403", description = "Forbidden request"),
        @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping( value = "/{id}", produces = "application/json")
    public ResponseEntity<MetricDto> findById(@PathVariable long id) {
        ResponseEntity<MetricDto> res = ResponseEntity.notFound().build();

        MetricDto metric =  MetricDto.toDto(metricService.findById(id));

        if (null != metric) {
            res = ResponseEntity.ok(metric);
        }

        return res;
    }

    
}
