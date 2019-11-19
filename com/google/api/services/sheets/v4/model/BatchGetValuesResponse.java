package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import java.util.List;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class BatchGetValuesResponse extends GenericJson
{
    @Key
    private String spreadsheetId;
    @Key
    private List<ValueRange> valueRanges;
    
    public BatchGetValuesResponse() {
        super();
    }
    
    public String getSpreadsheetId() {
        return this.spreadsheetId;
    }
    
    public BatchGetValuesResponse setSpreadsheetId(final String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
        return this;
    }
    
    public List<ValueRange> getValueRanges() {
        return this.valueRanges;
    }
    
    public BatchGetValuesResponse setValueRanges(final List<ValueRange> valueRanges) {
        this.valueRanges = valueRanges;
        return this;
    }
    
    @Override
    public BatchGetValuesResponse set(final String s, final Object o) {
        return (BatchGetValuesResponse)super.set(s, o);
    }
    
    @Override
    public BatchGetValuesResponse clone() {
        return (BatchGetValuesResponse)super.clone();
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
