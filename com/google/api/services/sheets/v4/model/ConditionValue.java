package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class ConditionValue extends GenericJson
{
    @Key
    private String relativeDate;
    @Key
    private String userEnteredValue;
    
    public ConditionValue() {
        super();
    }
    
    public String getRelativeDate() {
        return this.relativeDate;
    }
    
    public ConditionValue setRelativeDate(final String relativeDate) {
        this.relativeDate = relativeDate;
        return this;
    }
    
    public String getUserEnteredValue() {
        return this.userEnteredValue;
    }
    
    public ConditionValue setUserEnteredValue(final String userEnteredValue) {
        this.userEnteredValue = userEnteredValue;
        return this;
    }
    
    @Override
    public ConditionValue set(final String s, final Object o) {
        return (ConditionValue)super.set(s, o);
    }
    
    @Override
    public ConditionValue clone() {
        return (ConditionValue)super.clone();
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
