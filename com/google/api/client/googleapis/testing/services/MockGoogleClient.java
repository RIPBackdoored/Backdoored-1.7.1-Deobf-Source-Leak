package com.google.api.client.googleapis.testing.services;

import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.googleapis.services.AbstractGoogleClient;

@Beta
public class MockGoogleClient extends AbstractGoogleClient
{
    public MockGoogleClient(final HttpTransport httpTransport, final String s, final String s2, final ObjectParser objectParser, final HttpRequestInitializer httpRequestInitializer) {
        this(new Builder(httpTransport, s, s2, objectParser, httpRequestInitializer));
    }
    
    protected MockGoogleClient(final Builder builder) {
        super(builder);
    }
}
