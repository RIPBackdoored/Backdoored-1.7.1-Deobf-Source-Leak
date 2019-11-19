package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class InsertDimensionRequest extends GenericJson
{
    @Key
    private Boolean inheritFromBefore;
    @Key
    private DimensionRange range;
    
    public InsertDimensionRequest() {
        super();
    }
    
    public Boolean getInheritFromBefore() {
        return this.inheritFromBefore;
    }
    
    public InsertDimensionRequest setInheritFromBefore(final Boolean inheritFromBefore) {
        this.inheritFromBefore = inheritFromBefore;
        return this;
    }
    
    public DimensionRange getRange() {
        return this.range;
    }
    
    public InsertDimensionRequest setRange(final DimensionRange range) {
        this.range = range;
        return this;
    }
    
    @Override
    public InsertDimensionRequest set(final String s, final Object o) {
        return (InsertDimensionRequest)super.set(s, o);
    }
    
    @Override
    public InsertDimensionRequest clone() {
        return (InsertDimensionRequest)super.clone();
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
