package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class MatchedDeveloperMetadata extends GenericJson
{
    @Key
    private List<DataFilter> dataFilters;
    @Key
    private DeveloperMetadata developerMetadata;
    
    public MatchedDeveloperMetadata() {
        super();
    }
    
    public List<DataFilter> getDataFilters() {
        return this.dataFilters;
    }
    
    public MatchedDeveloperMetadata setDataFilters(final List<DataFilter> dataFilters) {
        this.dataFilters = dataFilters;
        return this;
    }
    
    public DeveloperMetadata getDeveloperMetadata() {
        return this.developerMetadata;
    }
    
    public MatchedDeveloperMetadata setDeveloperMetadata(final DeveloperMetadata developerMetadata) {
        this.developerMetadata = developerMetadata;
        return this;
    }
    
    @Override
    public MatchedDeveloperMetadata set(final String s, final Object o) {
        return (MatchedDeveloperMetadata)super.set(s, o);
    }
    
    @Override
    public MatchedDeveloperMetadata clone() {
        return (MatchedDeveloperMetadata)super.clone();
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
