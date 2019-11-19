package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class GradientRule extends GenericJson
{
    @Key
    private InterpolationPoint maxpoint;
    @Key
    private InterpolationPoint midpoint;
    @Key
    private InterpolationPoint minpoint;
    
    public GradientRule() {
        super();
    }
    
    public InterpolationPoint getMaxpoint() {
        return this.maxpoint;
    }
    
    public GradientRule setMaxpoint(final InterpolationPoint maxpoint) {
        this.maxpoint = maxpoint;
        return this;
    }
    
    public InterpolationPoint getMidpoint() {
        return this.midpoint;
    }
    
    public GradientRule setMidpoint(final InterpolationPoint midpoint) {
        this.midpoint = midpoint;
        return this;
    }
    
    public InterpolationPoint getMinpoint() {
        return this.minpoint;
    }
    
    public GradientRule setMinpoint(final InterpolationPoint minpoint) {
        this.minpoint = minpoint;
        return this;
    }
    
    @Override
    public GradientRule set(final String s, final Object o) {
        return (GradientRule)super.set(s, o);
    }
    
    @Override
    public GradientRule clone() {
        return (GradientRule)super.clone();
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
