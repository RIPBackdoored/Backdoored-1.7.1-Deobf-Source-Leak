package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class MergeCellsRequest extends GenericJson
{
    @Key
    private String mergeType;
    @Key
    private GridRange range;
    
    public MergeCellsRequest() {
        super();
    }
    
    public String getMergeType() {
        return this.mergeType;
    }
    
    public MergeCellsRequest setMergeType(final String mergeType) {
        this.mergeType = mergeType;
        return this;
    }
    
    public GridRange getRange() {
        return this.range;
    }
    
    public MergeCellsRequest setRange(final GridRange range) {
        this.range = range;
        return this;
    }
    
    @Override
    public MergeCellsRequest set(final String s, final Object o) {
        return (MergeCellsRequest)super.set(s, o);
    }
    
    @Override
    public MergeCellsRequest clone() {
        return (MergeCellsRequest)super.clone();
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
