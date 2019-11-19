package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class AddChartRequest extends GenericJson
{
    @Key
    private EmbeddedChart chart;
    
    public AddChartRequest() {
        super();
    }
    
    public EmbeddedChart getChart() {
        return this.chart;
    }
    
    public AddChartRequest setChart(final EmbeddedChart chart) {
        this.chart = chart;
        return this;
    }
    
    @Override
    public AddChartRequest set(final String s, final Object o) {
        return (AddChartRequest)super.set(s, o);
    }
    
    @Override
    public AddChartRequest clone() {
        return (AddChartRequest)super.clone();
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
