package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class AddDimensionGroupRequest extends GenericJson
{
    @Key
    private DimensionRange range;
    
    public AddDimensionGroupRequest() {
        super();
    }
    
    public DimensionRange getRange() {
        return this.range;
    }
    
    public AddDimensionGroupRequest setRange(final DimensionRange range) {
        this.range = range;
        return this;
    }
    
    @Override
    public AddDimensionGroupRequest set(final String s, final Object o) {
        return (AddDimensionGroupRequest)super.set(s, o);
    }
    
    @Override
    public AddDimensionGroupRequest clone() {
        return (AddDimensionGroupRequest)super.clone();
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
