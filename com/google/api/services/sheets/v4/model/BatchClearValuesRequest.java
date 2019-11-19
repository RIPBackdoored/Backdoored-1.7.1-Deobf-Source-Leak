package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class BatchClearValuesRequest extends GenericJson
{
    @Key
    private List<String> ranges;
    
    public BatchClearValuesRequest() {
        super();
    }
    
    public List<String> getRanges() {
        return this.ranges;
    }
    
    public BatchClearValuesRequest setRanges(final List<String> ranges) {
        this.ranges = ranges;
        return this;
    }
    
    @Override
    public BatchClearValuesRequest set(final String s, final Object o) {
        return (BatchClearValuesRequest)super.set(s, o);
    }
    
    @Override
    public BatchClearValuesRequest clone() {
        return (BatchClearValuesRequest)super.clone();
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
