package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class DuplicateFilterViewResponse extends GenericJson
{
    @Key
    private FilterView filter;
    
    public DuplicateFilterViewResponse() {
        super();
    }
    
    public FilterView getFilter() {
        return this.filter;
    }
    
    public DuplicateFilterViewResponse setFilter(final FilterView filter) {
        this.filter = filter;
        return this;
    }
    
    @Override
    public DuplicateFilterViewResponse set(final String s, final Object o) {
        return (DuplicateFilterViewResponse)super.set(s, o);
    }
    
    @Override
    public DuplicateFilterViewResponse clone() {
        return (DuplicateFilterViewResponse)super.clone();
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
