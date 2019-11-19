package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class UpdateCellsRequest extends GenericJson
{
    @Key
    private String fields;
    @Key
    private GridRange range;
    @Key
    private List<RowData> rows;
    @Key
    private GridCoordinate start;
    
    public UpdateCellsRequest() {
        super();
    }
    
    public String getFields() {
        return this.fields;
    }
    
    public UpdateCellsRequest setFields(final String fields) {
        this.fields = fields;
        return this;
    }
    
    public GridRange getRange() {
        return this.range;
    }
    
    public UpdateCellsRequest setRange(final GridRange range) {
        this.range = range;
        return this;
    }
    
    public List<RowData> getRows() {
        return this.rows;
    }
    
    public UpdateCellsRequest setRows(final List<RowData> rows) {
        this.rows = rows;
        return this;
    }
    
    public GridCoordinate getStart() {
        return this.start;
    }
    
    public UpdateCellsRequest setStart(final GridCoordinate start) {
        this.start = start;
        return this;
    }
    
    @Override
    public UpdateCellsRequest set(final String s, final Object o) {
        return (UpdateCellsRequest)super.set(s, o);
    }
    
    @Override
    public UpdateCellsRequest clone() {
        return (UpdateCellsRequest)super.clone();
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
