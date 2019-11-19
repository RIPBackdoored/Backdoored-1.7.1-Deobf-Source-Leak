package com.google.api.client.json.webtoken;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public static class Header extends GenericJson
{
    @Key("typ")
    private String type;
    @Key("cty")
    private String contentType;
    
    public Header() {
        super();
    }
    
    public final String getType() {
        return this.type;
    }
    
    public Header setType(final String type) {
        this.type = type;
        return this;
    }
    
    public final String getContentType() {
        return this.contentType;
    }
    
    public Header setContentType(final String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    @Override
    public Header set(final String s, final Object o) {
        return (Header)super.set(s, o);
    }
    
    @Override
    public Header clone() {
        return (Header)super.clone();
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
