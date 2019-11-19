package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class DeleteBandingRequest extends GenericJson
{
    @Key
    private Integer bandedRangeId;
    
    public DeleteBandingRequest() {
        super();
    }
    
    public Integer getBandedRangeId() {
        return this.bandedRangeId;
    }
    
    public DeleteBandingRequest setBandedRangeId(final Integer bandedRangeId) {
        this.bandedRangeId = bandedRangeId;
        return this;
    }
    
    @Override
    public DeleteBandingRequest set(final String s, final Object o) {
        return (DeleteBandingRequest)super.set(s, o);
    }
    
    @Override
    public DeleteBandingRequest clone() {
        return (DeleteBandingRequest)super.clone();
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
