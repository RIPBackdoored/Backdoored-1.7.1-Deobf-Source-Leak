package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class AddProtectedRangeResponse extends GenericJson
{
    @Key
    private ProtectedRange protectedRange;
    
    public AddProtectedRangeResponse() {
        super();
    }
    
    public ProtectedRange getProtectedRange() {
        return this.protectedRange;
    }
    
    public AddProtectedRangeResponse setProtectedRange(final ProtectedRange protectedRange) {
        this.protectedRange = protectedRange;
        return this;
    }
    
    @Override
    public AddProtectedRangeResponse set(final String s, final Object o) {
        return (AddProtectedRangeResponse)super.set(s, o);
    }
    
    @Override
    public AddProtectedRangeResponse clone() {
        return (AddProtectedRangeResponse)super.clone();
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
