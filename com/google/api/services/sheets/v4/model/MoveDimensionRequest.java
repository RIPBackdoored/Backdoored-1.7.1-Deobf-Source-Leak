package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class MoveDimensionRequest extends GenericJson
{
    @Key
    private Integer destinationIndex;
    @Key
    private DimensionRange source;
    
    public MoveDimensionRequest() {
        super();
    }
    
    public Integer getDestinationIndex() {
        return this.destinationIndex;
    }
    
    public MoveDimensionRequest setDestinationIndex(final Integer destinationIndex) {
        this.destinationIndex = destinationIndex;
        return this;
    }
    
    public DimensionRange getSource() {
        return this.source;
    }
    
    public MoveDimensionRequest setSource(final DimensionRange source) {
        this.source = source;
        return this;
    }
    
    @Override
    public MoveDimensionRequest set(final String s, final Object o) {
        return (MoveDimensionRequest)super.set(s, o);
    }
    
    @Override
    public MoveDimensionRequest clone() {
        return (MoveDimensionRequest)super.clone();
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
