package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class UpdateBandingRequest extends GenericJson
{
    @Key
    private BandedRange bandedRange;
    @Key
    private String fields;
    
    public UpdateBandingRequest() {
        super();
    }
    
    public BandedRange getBandedRange() {
        return this.bandedRange;
    }
    
    public UpdateBandingRequest setBandedRange(final BandedRange bandedRange) {
        this.bandedRange = bandedRange;
        return this;
    }
    
    public String getFields() {
        return this.fields;
    }
    
    public UpdateBandingRequest setFields(final String fields) {
        this.fields = fields;
        return this;
    }
    
    @Override
    public UpdateBandingRequest set(final String s, final Object o) {
        return (UpdateBandingRequest)super.set(s, o);
    }
    
    @Override
    public UpdateBandingRequest clone() {
        return (UpdateBandingRequest)super.clone();
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
