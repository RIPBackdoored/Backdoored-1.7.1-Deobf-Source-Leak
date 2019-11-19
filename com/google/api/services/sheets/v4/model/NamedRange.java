package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class NamedRange extends GenericJson
{
    @Key
    private String name;
    @Key
    private String namedRangeId;
    @Key
    private GridRange range;
    
    public NamedRange() {
        super();
    }
    
    public String getName() {
        return this.name;
    }
    
    public NamedRange setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getNamedRangeId() {
        return this.namedRangeId;
    }
    
    public NamedRange setNamedRangeId(final String namedRangeId) {
        this.namedRangeId = namedRangeId;
        return this;
    }
    
    public GridRange getRange() {
        return this.range;
    }
    
    public NamedRange setRange(final GridRange range) {
        this.range = range;
        return this;
    }
    
    @Override
    public NamedRange set(final String s, final Object o) {
        return (NamedRange)super.set(s, o);
    }
    
    @Override
    public NamedRange clone() {
        return (NamedRange)super.clone();
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
