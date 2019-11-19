package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class ChartData extends GenericJson
{
    @Key
    private ChartSourceRange sourceRange;
    
    public ChartData() {
        super();
    }
    
    public ChartSourceRange getSourceRange() {
        return this.sourceRange;
    }
    
    public ChartData setSourceRange(final ChartSourceRange sourceRange) {
        this.sourceRange = sourceRange;
        return this;
    }
    
    @Override
    public ChartData set(final String s, final Object o) {
        return (ChartData)super.set(s, o);
    }
    
    @Override
    public ChartData clone() {
        return (ChartData)super.clone();
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
