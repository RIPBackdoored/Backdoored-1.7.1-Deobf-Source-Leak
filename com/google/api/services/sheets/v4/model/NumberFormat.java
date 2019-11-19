package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class NumberFormat extends GenericJson
{
    @Key
    private String pattern;
    @Key
    private String type;
    
    public NumberFormat() {
        super();
    }
    
    public String getPattern() {
        return this.pattern;
    }
    
    public NumberFormat setPattern(final String pattern) {
        this.pattern = pattern;
        return this;
    }
    
    public String getType() {
        return this.type;
    }
    
    public NumberFormat setType(final String type) {
        this.type = type;
        return this;
    }
    
    @Override
    public NumberFormat set(final String s, final Object o) {
        return (NumberFormat)super.set(s, o);
    }
    
    @Override
    public NumberFormat clone() {
        return (NumberFormat)super.clone();
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
