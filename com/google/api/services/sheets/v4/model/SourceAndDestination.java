package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class SourceAndDestination extends GenericJson
{
    @Key
    private String dimension;
    @Key
    private Integer fillLength;
    @Key
    private GridRange source;
    
    public SourceAndDestination() {
        super();
    }
    
    public String getDimension() {
        return this.dimension;
    }
    
    public SourceAndDestination setDimension(final String dimension) {
        this.dimension = dimension;
        return this;
    }
    
    public Integer getFillLength() {
        return this.fillLength;
    }
    
    public SourceAndDestination setFillLength(final Integer fillLength) {
        this.fillLength = fillLength;
        return this;
    }
    
    public GridRange getSource() {
        return this.source;
    }
    
    public SourceAndDestination setSource(final GridRange source) {
        this.source = source;
        return this;
    }
    
    @Override
    public SourceAndDestination set(final String s, final Object o) {
        return (SourceAndDestination)super.set(s, o);
    }
    
    @Override
    public SourceAndDestination clone() {
        return (SourceAndDestination)super.clone();
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
