package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class MatchedValueRange extends GenericJson
{
    @Key
    private List<DataFilter> dataFilters;
    @Key
    private ValueRange valueRange;
    
    public MatchedValueRange() {
        super();
    }
    
    public List<DataFilter> getDataFilters() {
        return this.dataFilters;
    }
    
    public MatchedValueRange setDataFilters(final List<DataFilter> dataFilters) {
        this.dataFilters = dataFilters;
        return this;
    }
    
    public ValueRange getValueRange() {
        return this.valueRange;
    }
    
    public MatchedValueRange setValueRange(final ValueRange valueRange) {
        this.valueRange = valueRange;
        return this;
    }
    
    @Override
    public MatchedValueRange set(final String s, final Object o) {
        return (MatchedValueRange)super.set(s, o);
    }
    
    @Override
    public MatchedValueRange clone() {
        return (MatchedValueRange)super.clone();
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
