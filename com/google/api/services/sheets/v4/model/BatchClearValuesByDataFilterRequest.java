package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class BatchClearValuesByDataFilterRequest extends GenericJson
{
    @Key
    private List<DataFilter> dataFilters;
    
    public BatchClearValuesByDataFilterRequest() {
        super();
    }
    
    public List<DataFilter> getDataFilters() {
        return this.dataFilters;
    }
    
    public BatchClearValuesByDataFilterRequest setDataFilters(final List<DataFilter> dataFilters) {
        this.dataFilters = dataFilters;
        return this;
    }
    
    @Override
    public BatchClearValuesByDataFilterRequest set(final String s, final Object o) {
        return (BatchClearValuesByDataFilterRequest)super.set(s, o);
    }
    
    @Override
    public BatchClearValuesByDataFilterRequest clone() {
        return (BatchClearValuesByDataFilterRequest)super.clone();
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
