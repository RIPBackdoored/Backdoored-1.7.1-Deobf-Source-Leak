package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class ClearValuesResponse extends GenericJson
{
    @Key
    private String clearedRange;
    @Key
    private String spreadsheetId;
    
    public ClearValuesResponse() {
        super();
    }
    
    public String getClearedRange() {
        return this.clearedRange;
    }
    
    public ClearValuesResponse setClearedRange(final String clearedRange) {
        this.clearedRange = clearedRange;
        return this;
    }
    
    public String getSpreadsheetId() {
        return this.spreadsheetId;
    }
    
    public ClearValuesResponse setSpreadsheetId(final String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
        return this;
    }
    
    @Override
    public ClearValuesResponse set(final String s, final Object o) {
        return (ClearValuesResponse)super.set(s, o);
    }
    
    @Override
    public ClearValuesResponse clone() {
        return (ClearValuesResponse)super.clone();
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
