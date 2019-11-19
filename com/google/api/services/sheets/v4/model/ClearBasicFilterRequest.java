package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class ClearBasicFilterRequest extends GenericJson
{
    @Key
    private Integer sheetId;
    
    public ClearBasicFilterRequest() {
        super();
    }
    
    public Integer getSheetId() {
        return this.sheetId;
    }
    
    public ClearBasicFilterRequest setSheetId(final Integer sheetId) {
        this.sheetId = sheetId;
        return this;
    }
    
    @Override
    public ClearBasicFilterRequest set(final String s, final Object o) {
        return (ClearBasicFilterRequest)super.set(s, o);
    }
    
    @Override
    public ClearBasicFilterRequest clone() {
        return (ClearBasicFilterRequest)super.clone();
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
