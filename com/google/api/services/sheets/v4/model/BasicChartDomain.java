package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class BasicChartDomain extends GenericJson
{
    @Key
    private ChartData domain;
    @Key
    private Boolean reversed;
    
    public BasicChartDomain() {
        super();
    }
    
    public ChartData getDomain() {
        return this.domain;
    }
    
    public BasicChartDomain setDomain(final ChartData domain) {
        this.domain = domain;
        return this;
    }
    
    public Boolean getReversed() {
        return this.reversed;
    }
    
    public BasicChartDomain setReversed(final Boolean reversed) {
        this.reversed = reversed;
        return this;
    }
    
    @Override
    public BasicChartDomain set(final String s, final Object o) {
        return (BasicChartDomain)super.set(s, o);
    }
    
    @Override
    public BasicChartDomain clone() {
        return (BasicChartDomain)super.clone();
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
