package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class DeleteSheetRequest extends GenericJson
{
    @Key
    private Integer sheetId;
    
    public DeleteSheetRequest() {
        super();
    }
    
    public Integer getSheetId() {
        return this.sheetId;
    }
    
    public DeleteSheetRequest setSheetId(final Integer sheetId) {
        this.sheetId = sheetId;
        return this;
    }
    
    @Override
    public DeleteSheetRequest set(final String s, final Object o) {
        return (DeleteSheetRequest)super.set(s, o);
    }
    
    @Override
    public DeleteSheetRequest clone() {
        return (DeleteSheetRequest)super.clone();
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
