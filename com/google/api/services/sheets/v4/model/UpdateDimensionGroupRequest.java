package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class UpdateDimensionGroupRequest extends GenericJson
{
    @Key
    private DimensionGroup dimensionGroup;
    @Key
    private String fields;
    
    public UpdateDimensionGroupRequest() {
        super();
    }
    
    public DimensionGroup getDimensionGroup() {
        return this.dimensionGroup;
    }
    
    public UpdateDimensionGroupRequest setDimensionGroup(final DimensionGroup dimensionGroup) {
        this.dimensionGroup = dimensionGroup;
        return this;
    }
    
    public String getFields() {
        return this.fields;
    }
    
    public UpdateDimensionGroupRequest setFields(final String fields) {
        this.fields = fields;
        return this;
    }
    
    @Override
    public UpdateDimensionGroupRequest set(final String s, final Object o) {
        return (UpdateDimensionGroupRequest)super.set(s, o);
    }
    
    @Override
    public UpdateDimensionGroupRequest clone() {
        return (UpdateDimensionGroupRequest)super.clone();
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
