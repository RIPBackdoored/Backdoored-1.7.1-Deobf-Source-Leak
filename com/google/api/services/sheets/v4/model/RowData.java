package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class RowData extends GenericJson
{
    @Key
    private List<CellData> values;
    
    public RowData() {
        super();
    }
    
    public List<CellData> getValues() {
        return this.values;
    }
    
    public RowData setValues(final List<CellData> values) {
        this.values = values;
        return this;
    }
    
    @Override
    public RowData set(final String s, final Object o) {
        return (RowData)super.set(s, o);
    }
    
    @Override
    public RowData clone() {
        return (RowData)super.clone();
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
