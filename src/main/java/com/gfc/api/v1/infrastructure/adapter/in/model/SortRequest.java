package com.gfc.api.v1.infrastructure.adapter.in.model;

import java.util.Map;

public class SortRequest {

    private Map<Long, Integer> weightedMetrics;

    private int top;

    public Map<Long, Integer> getWeightedMetrics() {
        return weightedMetrics;
    }

    public void setWeightedMetrics(Map<Long, Integer> weightedMetrics) {
        this.weightedMetrics = weightedMetrics;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }    
}

