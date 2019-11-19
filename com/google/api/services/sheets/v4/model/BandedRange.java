package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class BandedRange extends GenericJson
{
    @Key
    private Integer bandedRangeId;
    @Key
    private BandingProperties columnProperties;
    @Key
    private GridRange range;
    @Key
    private BandingProperties rowProperties;
    
    public BandedRange() {
        super();
    }
    
    public Integer getBandedRangeId() {
        return this.bandedRangeId;
    }
    
    public BandedRange setBandedRangeId(final Integer bandedRangeId) {
        this.bandedRangeId = bandedRangeId;
        return this;
    }
    
    public BandingProperties getColumnProperties() {
        return this.columnProperties;
    }
    
    public BandedRange setColumnProperties(final BandingProperties columnProperties) {
        this.columnProperties = columnProperties;
        return this;
    }
    
    public GridRange getRange() {
        return this.range;
    }
    
    public BandedRange setRange(final GridRange range) {
        this.range = range;
        return this;
    }
    
    public BandingProperties getRowProperties() {
        return this.rowProperties;
    }
    
    public BandedRange setRowProperties(final BandingProperties rowProperties) {
        this.rowProperties = rowProperties;
        return this;
    }
    
    @Override
    public BandedRange set(final String s, final Object o) {
        return (BandedRange)super.set(s, o);
    }
    
    @Override
    public BandedRange clone() {
        return (BandedRange)super.clone();
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
