package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import java.util.List;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class WaterfallChartSpec extends GenericJson
{
    @Key
    private LineStyle connectorLineStyle;
    @Key
    private WaterfallChartDomain domain;
    @Key
    private Boolean firstValueIsTotal;
    @Key
    private Boolean hideConnectorLines;
    @Key
    private List<WaterfallChartSeries> series;
    @Key
    private String stackedType;
    
    public WaterfallChartSpec() {
        super();
    }
    
    public LineStyle getConnectorLineStyle() {
        return this.connectorLineStyle;
    }
    
    public WaterfallChartSpec setConnectorLineStyle(final LineStyle connectorLineStyle) {
        this.connectorLineStyle = connectorLineStyle;
        return this;
    }
    
    public WaterfallChartDomain getDomain() {
        return this.domain;
    }
    
    public WaterfallChartSpec setDomain(final WaterfallChartDomain domain) {
        this.domain = domain;
        return this;
    }
    
    public Boolean getFirstValueIsTotal() {
        return this.firstValueIsTotal;
    }
    
    public WaterfallChartSpec setFirstValueIsTotal(final Boolean firstValueIsTotal) {
        this.firstValueIsTotal = firstValueIsTotal;
        return this;
    }
    
    public Boolean getHideConnectorLines() {
        return this.hideConnectorLines;
    }
    
    public WaterfallChartSpec setHideConnectorLines(final Boolean hideConnectorLines) {
        this.hideConnectorLines = hideConnectorLines;
        return this;
    }
    
    public List<WaterfallChartSeries> getSeries() {
        return this.series;
    }
    
    public WaterfallChartSpec setSeries(final List<WaterfallChartSeries> series) {
        this.series = series;
        return this;
    }
    
    public String getStackedType() {
        return this.stackedType;
    }
    
    public WaterfallChartSpec setStackedType(final String stackedType) {
        this.stackedType = stackedType;
        return this;
    }
    
    @Override
    public WaterfallChartSpec set(final String s, final Object o) {
        return (WaterfallChartSpec)super.set(s, o);
    }
    
    @Override
    public WaterfallChartSpec clone() {
        return (WaterfallChartSpec)super.clone();
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
