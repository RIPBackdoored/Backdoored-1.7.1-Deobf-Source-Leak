package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class SortRangeRequest extends GenericJson
{
    @Key
    private GridRange range;
    @Key
    private List<SortSpec> sortSpecs;
    
    public SortRangeRequest() {
        super();
    }
    
    public GridRange getRange() {
        return this.range;
    }
    
    public SortRangeRequest setRange(final GridRange range) {
        this.range = range;
        return this;
    }
    
    public List<SortSpec> getSortSpecs() {
        return this.sortSpecs;
    }
    
    public SortRangeRequest setSortSpecs(final List<SortSpec> sortSpecs) {
        this.sortSpecs = sortSpecs;
        return this;
    }
    
    @Override
    public SortRangeRequest set(final String s, final Object o) {
        return (SortRangeRequest)super.set(s, o);
    }
    
    @Override
    public SortRangeRequest clone() {
        return (SortRangeRequest)super.clone();
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
        Data.<Object>nullOf(SortSpec.class);
    }
}
