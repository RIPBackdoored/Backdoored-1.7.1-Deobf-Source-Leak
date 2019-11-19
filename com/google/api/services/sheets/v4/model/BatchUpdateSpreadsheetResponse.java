package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class BatchUpdateSpreadsheetResponse extends GenericJson
{
    @Key
    private List<Response> replies;
    @Key
    private String spreadsheetId;
    @Key
    private Spreadsheet updatedSpreadsheet;
    
    public BatchUpdateSpreadsheetResponse() {
        super();
    }
    
    public List<Response> getReplies() {
        return this.replies;
    }
    
    public BatchUpdateSpreadsheetResponse setReplies(final List<Response> replies) {
        this.replies = replies;
        return this;
    }
    
    public String getSpreadsheetId() {
        return this.spreadsheetId;
    }
    
    public BatchUpdateSpreadsheetResponse setSpreadsheetId(final String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
        return this;
    }
    
    public Spreadsheet getUpdatedSpreadsheet() {
        return this.updatedSpreadsheet;
    }
    
    public BatchUpdateSpreadsheetResponse setUpdatedSpreadsheet(final Spreadsheet updatedSpreadsheet) {
        this.updatedSpreadsheet = updatedSpreadsheet;
        return this;
    }
    
    @Override
    public BatchUpdateSpreadsheetResponse set(final String s, final Object o) {
        return (BatchUpdateSpreadsheetResponse)super.set(s, o);
    }
    
    @Override
    public BatchUpdateSpreadsheetResponse clone() {
        return (BatchUpdateSpreadsheetResponse)super.clone();
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
        Data.<Object>nullOf(Response.class);
    }
}
