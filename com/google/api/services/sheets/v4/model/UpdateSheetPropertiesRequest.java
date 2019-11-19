package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class UpdateSheetPropertiesRequest extends GenericJson
{
    @Key
    private String fields;
    @Key
    private SheetProperties properties;
    
    public UpdateSheetPropertiesRequest() {
        super();
    }
    
    public String getFields() {
        return this.fields;
    }
    
    public UpdateSheetPropertiesRequest setFields(final String fields) {
        this.fields = fields;
        return this;
    }
    
    public SheetProperties getProperties() {
        return this.properties;
    }
    
    public UpdateSheetPropertiesRequest setProperties(final SheetProperties properties) {
        this.properties = properties;
        return this;
    }
    
    @Override
    public UpdateSheetPropertiesRequest set(final String s, final Object o) {
        return (UpdateSheetPropertiesRequest)super.set(s, o);
    }
    
    @Override
    public UpdateSheetPropertiesRequest clone() {
        return (UpdateSheetPropertiesRequest)super.clone();
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
