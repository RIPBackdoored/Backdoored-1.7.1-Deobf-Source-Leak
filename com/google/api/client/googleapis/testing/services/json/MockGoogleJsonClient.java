package com.google.api.client.googleapis.testing.services.json;

import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;

@Beta
public class MockGoogleJsonClient extends AbstractGoogleJsonClient
{
    protected MockGoogleJsonClient(final Builder builder) {
        super(builder);
    }
    
    public MockGoogleJsonClient(final HttpTransport httpTransport, final JsonFactory jsonFactory, final String s, final String s2, final HttpRequestInitializer httpRequestInitializer, final boolean b) {
        this(new Builder(httpTransport, jsonFactory, s, s2, httpRequestInitializer, b));
    }
}
