package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class TextFormatRun extends GenericJson
{
    @Key
    private TextFormat format;
    @Key
    private Integer startIndex;
    
    public TextFormatRun() {
        super();
    }
    
    public TextFormat getFormat() {
        return this.format;
    }
    
    public TextFormatRun setFormat(final TextFormat format) {
        this.format = format;
        return this;
    }
    
    public Integer getStartIndex() {
        return this.startIndex;
    }
    
    public TextFormatRun setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
        return this;
    }
    
    @Override
    public TextFormatRun set(final String s, final Object o) {
        return (TextFormatRun)super.set(s, o);
    }
    
    @Override
    public TextFormatRun clone() {
        return (TextFormatRun)super.clone();
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
