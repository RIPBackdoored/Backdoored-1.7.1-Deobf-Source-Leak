package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import java.util.List;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class FilterCriteria extends GenericJson
{
    @Key
    private BooleanCondition condition;
    @Key
    private List<String> hiddenValues;
    
    public FilterCriteria() {
        super();
    }
    
    public BooleanCondition getCondition() {
        return this.condition;
    }
    
    public FilterCriteria setCondition(final BooleanCondition condition) {
        this.condition = condition;
        return this;
    }
    
    public List<String> getHiddenValues() {
        return this.hiddenValues;
    }
    
    public FilterCriteria setHiddenValues(final List<String> hiddenValues) {
        this.hiddenValues = hiddenValues;
        return this;
    }
    
    @Override
    public FilterCriteria set(final String s, final Object o) {
        return (FilterCriteria)super.set(s, o);
    }
    
    @Override
    public FilterCriteria clone() {
        return (FilterCriteria)super.clone();
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
