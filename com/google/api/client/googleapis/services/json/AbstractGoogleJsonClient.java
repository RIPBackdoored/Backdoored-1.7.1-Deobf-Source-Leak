package com.google.api.client.googleapis.services.json;

import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.googleapis.services.AbstractGoogleClient;

public abstract class AbstractGoogleJsonClient extends AbstractGoogleClient
{
    protected AbstractGoogleJsonClient(final Builder builder) {
        super(builder);
    }
    
    @Override
    public JsonObjectParser getObjectParser() {
        return (JsonObjectParser)super.getObjectParser();
    }
    
    public final JsonFactory getJsonFactory() {
        return this.getObjectParser().getJsonFactory();
    }
    
    @Override
    public /* bridge */ ObjectParser getObjectParser() {
        return this.getObjectParser();
    }
}
