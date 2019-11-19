package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class DeleteDimensionGroupResponse extends GenericJson
{
    @Key
    private List<DimensionGroup> dimensionGroups;
    
    public DeleteDimensionGroupResponse() {
        super();
    }
    
    public List<DimensionGroup> getDimensionGroups() {
        return this.dimensionGroups;
    }
    
    public DeleteDimensionGroupResponse setDimensionGroups(final List<DimensionGroup> dimensionGroups) {
        this.dimensionGroups = dimensionGroups;
        return this;
    }
    
    @Override
    public DeleteDimensionGroupResponse set(final String s, final Object o) {
        return (DeleteDimensionGroupResponse)super.set(s, o);
    }
    
    @Override
    public DeleteDimensionGroupResponse clone() {
        return (DeleteDimensionGroupResponse)super.clone();
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
