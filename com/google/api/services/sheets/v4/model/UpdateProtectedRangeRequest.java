package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class UpdateProtectedRangeRequest extends GenericJson
{
    @Key
    private String fields;
    @Key
    private ProtectedRange protectedRange;
    
    public UpdateProtectedRangeRequest() {
        super();
    }
    
    public String getFields() {
        return this.fields;
    }
    
    public UpdateProtectedRangeRequest setFields(final String fields) {
        this.fields = fields;
        return this;
    }
    
    public ProtectedRange getProtectedRange() {
        return this.protectedRange;
    }
    
    public UpdateProtectedRangeRequest setProtectedRange(final ProtectedRange protectedRange) {
        this.protectedRange = protectedRange;
        return this;
    }
    
    @Override
    public UpdateProtectedRangeRequest set(final String s, final Object o) {
        return (UpdateProtectedRangeRequest)super.set(s, o);
    }
    
    @Override
    public UpdateProtectedRangeRequest clone() {
        return (UpdateProtectedRangeRequest)super.clone();
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
