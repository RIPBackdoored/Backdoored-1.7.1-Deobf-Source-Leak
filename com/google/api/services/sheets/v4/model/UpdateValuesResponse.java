package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class UpdateValuesResponse extends GenericJson
{
    @Key
    private String spreadsheetId;
    @Key
    private Integer updatedCells;
    @Key
    private Integer updatedColumns;
    @Key
    private ValueRange updatedData;
    @Key
    private String updatedRange;
    @Key
    private Integer updatedRows;
    
    public UpdateValuesResponse() {
        super();
    }
    
    public String getSpreadsheetId() {
        return this.spreadsheetId;
    }
    
    public UpdateValuesResponse setSpreadsheetId(final String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
        return this;
    }
    
    public Integer getUpdatedCells() {
        return this.updatedCells;
    }
    
    public UpdateValuesResponse setUpdatedCells(final Integer updatedCells) {
        this.updatedCells = updatedCells;
        return this;
    }
    
    public Integer getUpdatedColumns() {
        return this.updatedColumns;
    }
    
    public UpdateValuesResponse setUpdatedColumns(final Integer updatedColumns) {
        this.updatedColumns = updatedColumns;
        return this;
    }
    
    public ValueRange getUpdatedData() {
        return this.updatedData;
    }
    
    public UpdateValuesResponse setUpdatedData(final ValueRange updatedData) {
        this.updatedData = updatedData;
        return this;
    }
    
    public String getUpdatedRange() {
        return this.updatedRange;
    }
    
    public UpdateValuesResponse setUpdatedRange(final String updatedRange) {
        this.updatedRange = updatedRange;
        return this;
    }
    
    public Integer getUpdatedRows() {
        return this.updatedRows;
    }
    
    public UpdateValuesResponse setUpdatedRows(final Integer updatedRows) {
        this.updatedRows = updatedRows;
        return this;
    }
    
    @Override
    public UpdateValuesResponse set(final String s, final Object o) {
        return (UpdateValuesResponse)super.set(s, o);
    }
    
    @Override
    public UpdateValuesResponse clone() {
        return (UpdateValuesResponse)super.clone();
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
