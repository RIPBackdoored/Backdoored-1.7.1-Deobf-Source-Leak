package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class DeleteFilterViewRequest extends GenericJson
{
    @Key
    private Integer filterId;
    
    public DeleteFilterViewRequest() {
        super();
    }
    
    public Integer getFilterId() {
        return this.filterId;
    }
    
    public DeleteFilterViewRequest setFilterId(final Integer filterId) {
        this.filterId = filterId;
        return this;
    }
    
    @Override
    public DeleteFilterViewRequest set(final String s, final Object o) {
        return (DeleteFilterViewRequest)super.set(s, o);
    }
    
    @Override
    public DeleteFilterViewRequest clone() {
        return (DeleteFilterViewRequest)super.clone();
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
