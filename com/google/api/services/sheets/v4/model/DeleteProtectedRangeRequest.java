package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class DeleteProtectedRangeRequest extends GenericJson
{
    @Key
    private Integer protectedRangeId;
    
    public DeleteProtectedRangeRequest() {
        super();
    }
    
    public Integer getProtectedRangeId() {
        return this.protectedRangeId;
    }
    
    public DeleteProtectedRangeRequest setProtectedRangeId(final Integer protectedRangeId) {
        this.protectedRangeId = protectedRangeId;
        return this;
    }
    
    @Override
    public DeleteProtectedRangeRequest set(final String s, final Object o) {
        return (DeleteProtectedRangeRequest)super.set(s, o);
    }
    
    @Override
    public DeleteProtectedRangeRequest clone() {
        return (DeleteProtectedRangeRequest)super.clone();
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
