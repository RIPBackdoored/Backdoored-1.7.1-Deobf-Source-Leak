package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class CopySheetToAnotherSpreadsheetRequest extends GenericJson
{
    @Key
    private String destinationSpreadsheetId;
    
    public CopySheetToAnotherSpreadsheetRequest() {
        super();
    }
    
    public String getDestinationSpreadsheetId() {
        return this.destinationSpreadsheetId;
    }
    
    public CopySheetToAnotherSpreadsheetRequest setDestinationSpreadsheetId(final String destinationSpreadsheetId) {
        this.destinationSpreadsheetId = destinationSpreadsheetId;
        return this;
    }
    
    @Override
    public CopySheetToAnotherSpreadsheetRequest set(final String s, final Object o) {
        return (CopySheetToAnotherSpreadsheetRequest)super.set(s, o);
    }
    
    @Override
    public CopySheetToAnotherSpreadsheetRequest clone() {
        return (CopySheetToAnotherSpreadsheetRequest)super.clone();
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
