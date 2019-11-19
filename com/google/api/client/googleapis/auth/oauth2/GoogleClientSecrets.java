package com.google.api.client.googleapis.auth.oauth2;

import java.util.List;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.io.Reader;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class GoogleClientSecrets extends GenericJson
{
    @Key
    private Details installed;
    @Key
    private Details web;
    
    public GoogleClientSecrets() {
        super();
    }
    
    public Details getInstalled() {
        return this.installed;
    }
    
    public GoogleClientSecrets setInstalled(final Details installed) {
        this.installed = installed;
        return this;
    }
    
    public Details getWeb() {
        return this.web;
    }
    
    public GoogleClientSecrets setWeb(final Details web) {
        this.web = web;
        return this;
    }
    
    public Details getDetails() {
        Preconditions.checkArgument(this.web == null != (this.installed == null));
        return (this.web == null) ? this.installed : this.web;
    }
    
    @Override
    public GoogleClientSecrets set(final String s, final Object o) {
        return (GoogleClientSecrets)super.set(s, o);
    }
    
    @Override
    public GoogleClientSecrets clone() {
        return (GoogleClientSecrets)super.clone();
    }
    
    public static GoogleClientSecrets load(final JsonFactory jsonFactory, final Reader reader) throws IOException {
        return jsonFactory.<GoogleClientSecrets>fromReader(reader, GoogleClientSecrets.class);
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
