package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class AppendValuesResponse extends GenericJson
{
    @Key
    private String spreadsheetId;
    @Key
    private String tableRange;
    @Key
    private UpdateValuesResponse updates;
    
    public AppendValuesResponse() {
        super();
    }
    
    public String getSpreadsheetId() {
        return this.spreadsheetId;
    }
    
    public AppendValuesResponse setSpreadsheetId(final String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
        return this;
    }
    
    public String getTableRange() {
        return this.tableRange;
    }
    
    public AppendValuesResponse setTableRange(final String tableRange) {
        this.tableRange = tableRange;
        return this;
    }
    
    public UpdateValuesResponse getUpdates() {
        return this.updates;
    }
    
    public AppendValuesResponse setUpdates(final UpdateValuesResponse updates) {
        this.updates = updates;
        return this;
    }
    
    @Override
    public AppendValuesResponse set(final String s, final Object o) {
        return (AppendValuesResponse)super.set(s, o);
    }
    
    @Override
    public AppendValuesResponse clone() {
        return (AppendValuesResponse)super.clone();
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
