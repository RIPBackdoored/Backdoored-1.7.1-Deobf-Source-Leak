package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class ChartSourceRange extends GenericJson
{
    @Key
    private List<GridRange> sources;
    
    public ChartSourceRange() {
        super();
    }
    
    public List<GridRange> getSources() {
        return this.sources;
    }
    
    public ChartSourceRange setSources(final List<GridRange> sources) {
        this.sources = sources;
        return this;
    }
    
    @Override
    public ChartSourceRange set(final String s, final Object o) {
        return (ChartSourceRange)super.set(s, o);
    }
    
    @Override
    public ChartSourceRange clone() {
        return (ChartSourceRange)super.clone();
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
    
    static {
        Data.<Object>nullOf(GridRange.class);
    }
}
