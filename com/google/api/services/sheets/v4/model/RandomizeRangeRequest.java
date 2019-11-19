package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class RandomizeRangeRequest extends GenericJson
{
    @Key
    private GridRange range;
    
    public RandomizeRangeRequest() {
        super();
    }
    
    public GridRange getRange() {
        return this.range;
    }
    
    public RandomizeRangeRequest setRange(final GridRange range) {
        this.range = range;
        return this;
    }
    
    @Override
    public RandomizeRangeRequest set(final String s, final Object o) {
        return (RandomizeRangeRequest)super.set(s, o);
    }
    
    @Override
    public RandomizeRangeRequest clone() {
        return (RandomizeRangeRequest)super.clone();
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
