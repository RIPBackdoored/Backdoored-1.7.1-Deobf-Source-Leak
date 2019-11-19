package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class UpdateDimensionPropertiesRequest extends GenericJson
{
    @Key
    private String fields;
    @Key
    private DimensionProperties properties;
    @Key
    private DimensionRange range;
    
    public UpdateDimensionPropertiesRequest() {
        super();
    }
    
    public String getFields() {
        return this.fields;
    }
    
    public UpdateDimensionPropertiesRequest setFields(final String fields) {
        this.fields = fields;
        return this;
    }
    
    public DimensionProperties getProperties() {
        return this.properties;
    }
    
    public UpdateDimensionPropertiesRequest setProperties(final DimensionProperties properties) {
        this.properties = properties;
        return this;
    }
    
    public DimensionRange getRange() {
        return this.range;
    }
    
    public UpdateDimensionPropertiesRequest setRange(final DimensionRange range) {
        this.range = range;
        return this;
    }
    
    @Override
    public UpdateDimensionPropertiesRequest set(final String s, final Object o) {
        return (UpdateDimensionPropertiesRequest)super.set(s, o);
    }
    
    @Override
    public UpdateDimensionPropertiesRequest clone() {
        return (UpdateDimensionPropertiesRequest)super.clone();
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
