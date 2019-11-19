package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class SortSpec extends GenericJson
{
    @Key
    private Integer dimensionIndex;
    @Key
    private String sortOrder;
    
    public SortSpec() {
        super();
    }
    
    public Integer getDimensionIndex() {
        return this.dimensionIndex;
    }
    
    public SortSpec setDimensionIndex(final Integer dimensionIndex) {
        this.dimensionIndex = dimensionIndex;
        return this;
    }
    
    public String getSortOrder() {
        return this.sortOrder;
    }
    
    public SortSpec setSortOrder(final String sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }
    
    @Override
    public SortSpec set(final String s, final Object o) {
        return (SortSpec)super.set(s, o);
    }
    
    @Override
    public SortSpec clone() {
        return (SortSpec)super.clone();
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
