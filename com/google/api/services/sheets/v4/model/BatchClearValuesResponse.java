package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class BatchClearValuesResponse extends GenericJson
{
    @Key
    private List<String> clearedRanges;
    @Key
    private String spreadsheetId;
    
    public BatchClearValuesResponse() {
        super();
    }
    
    public List<String> getClearedRanges() {
        return this.clearedRanges;
    }
    
    public BatchClearValuesResponse setClearedRanges(final List<String> clearedRanges) {
        this.clearedRanges = clearedRanges;
        return this;
    }
    
    public String getSpreadsheetId() {
        return this.spreadsheetId;
    }
    
    public BatchClearValuesResponse setSpreadsheetId(final String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
        return this;
    }
    
    @Override
    public BatchClearValuesResponse set(final String s, final Object o) {
        return (BatchClearValuesResponse)super.set(s, o);
    }
    
    @Override
    public BatchClearValuesResponse clone() {
        return (BatchClearValuesResponse)super.clone();
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
