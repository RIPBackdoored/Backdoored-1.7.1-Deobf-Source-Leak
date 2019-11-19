package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class AddProtectedRangeRequest extends GenericJson
{
    @Key
    private ProtectedRange protectedRange;
    
    public AddProtectedRangeRequest() {
        super();
    }
    
    public ProtectedRange getProtectedRange() {
        return this.protectedRange;
    }
    
    public AddProtectedRangeRequest setProtectedRange(final ProtectedRange protectedRange) {
        this.protectedRange = protectedRange;
        return this;
    }
    
    @Override
    public AddProtectedRangeRequest set(final String s, final Object o) {
        return (AddProtectedRangeRequest)super.set(s, o);
    }
    
    @Override
    public AddProtectedRangeRequest clone() {
        return (AddProtectedRangeRequest)super.clone();
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
