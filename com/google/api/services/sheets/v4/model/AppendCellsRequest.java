package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class AppendCellsRequest extends GenericJson
{
    @Key
    private String fields;
    @Key
    private List<RowData> rows;
    @Key
    private Integer sheetId;
    
    public AppendCellsRequest() {
        super();
    }
    
    public String getFields() {
        return this.fields;
    }
    
    public AppendCellsRequest setFields(final String fields) {
        this.fields = fields;
        return this;
    }
    
    public List<RowData> getRows() {
        return this.rows;
    }
    
    public AppendCellsRequest setRows(final List<RowData> rows) {
        this.rows = rows;
        return this;
    }
    
    public Integer getSheetId() {
        return this.sheetId;
    }
    
    public AppendCellsRequest setSheetId(final Integer sheetId) {
        this.sheetId = sheetId;
        return this;
    }
    
    @Override
    public AppendCellsRequest set(final String s, final Object o) {
        return (AppendCellsRequest)super.set(s, o);
    }
    
    @Override
    public AppendCellsRequest clone() {
        return (AppendCellsRequest)super.clone();
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
    
    static {
        Data.<Object>nullOf(RowData.class);
    }
}
