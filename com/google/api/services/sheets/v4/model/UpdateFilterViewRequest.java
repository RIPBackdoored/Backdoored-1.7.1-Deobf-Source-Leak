package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class UpdateFilterViewRequest extends GenericJson
{
    @Key
    private String fields;
    @Key
    private FilterView filter;
    
    public UpdateFilterViewRequest() {
        super();
    }
    
    public String getFields() {
        return this.fields;
    }
    
    public UpdateFilterViewRequest setFields(final String fields) {
        this.fields = fields;
        return this;
    }
    
    public FilterView getFilter() {
        return this.filter;
    }
    
    public UpdateFilterViewRequest setFilter(final FilterView filter) {
        this.filter = filter;
        return this;
    }
    
    @Override
    public UpdateFilterViewRequest set(final String s, final Object o) {
        return (UpdateFilterViewRequest)super.set(s, o);
    }
    
    @Override
    public UpdateFilterViewRequest clone() {
        return (UpdateFilterViewRequest)super.clone();
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
