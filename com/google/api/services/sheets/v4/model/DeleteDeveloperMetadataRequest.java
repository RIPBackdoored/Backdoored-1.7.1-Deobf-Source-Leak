package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class DeleteDeveloperMetadataRequest extends GenericJson
{
    @Key
    private DataFilter dataFilter;
    
    public DeleteDeveloperMetadataRequest() {
        super();
    }
    
    public DataFilter getDataFilter() {
        return this.dataFilter;
    }
    
    public DeleteDeveloperMetadataRequest setDataFilter(final DataFilter dataFilter) {
        this.dataFilter = dataFilter;
        return this;
    }
    
    @Override
    public DeleteDeveloperMetadataRequest set(final String s, final Object o) {
        return (DeleteDeveloperMetadataRequest)super.set(s, o);
    }
    
    @Override
    public DeleteDeveloperMetadataRequest clone() {
        return (DeleteDeveloperMetadataRequest)super.clone();
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
