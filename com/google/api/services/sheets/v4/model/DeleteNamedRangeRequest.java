package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class DeleteNamedRangeRequest extends GenericJson
{
    @Key
    private String namedRangeId;
    
    public DeleteNamedRangeRequest() {
        super();
    }
    
    public String getNamedRangeId() {
        return this.namedRangeId;
    }
    
    public DeleteNamedRangeRequest setNamedRangeId(final String namedRangeId) {
        this.namedRangeId = namedRangeId;
        return this;
    }
    
    @Override
    public DeleteNamedRangeRequest set(final String s, final Object o) {
        return (DeleteNamedRangeRequest)super.set(s, o);
    }
    
    @Override
    public DeleteNamedRangeRequest clone() {
        return (DeleteNamedRangeRequest)super.clone();
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
