package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class UpdateChartSpecRequest extends GenericJson
{
    @Key
    private Integer chartId;
    @Key
    private ChartSpec spec;
    
    public UpdateChartSpecRequest() {
        super();
    }
    
    public Integer getChartId() {
        return this.chartId;
    }
    
    public UpdateChartSpecRequest setChartId(final Integer chartId) {
        this.chartId = chartId;
        return this;
    }
    
    public ChartSpec getSpec() {
        return this.spec;
    }
    
    public UpdateChartSpecRequest setSpec(final ChartSpec spec) {
        this.spec = spec;
        return this;
    }
    
    @Override
    public UpdateChartSpecRequest set(final String s, final Object o) {
        return (UpdateChartSpecRequest)super.set(s, o);
    }
    
    @Override
    public UpdateChartSpecRequest clone() {
        return (UpdateChartSpecRequest)super.clone();
    }
    
    @Override
    public /* bridge */ GenericJson set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ GenericJson clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ GenericData clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    public /* bridge */ Object clone() throws CloneNotSupportedException {
        return this.clone();
    }
}
