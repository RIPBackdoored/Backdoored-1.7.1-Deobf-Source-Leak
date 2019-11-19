package com.google.api.client.googleapis.testing.services.json;

import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.util.Beta;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;

@Beta
public class MockGoogleJsonClientRequest<T> extends AbstractGoogleJsonClientRequest<T>
{
    public MockGoogleJsonClientRequest(final AbstractGoogleJsonClient abstractGoogleJsonClient, final String s, final String s2, final Object o, final Class<T> clazz) {
        super(abstractGoogleJsonClient, s, s2, o, clazz);
    }
    
    @Override
    public MockGoogleJsonClient getAbstractGoogleClient() {
        return (MockGoogleJsonClient)super.getAbstractGoogleClient();
    }
    
    @Override
    public MockGoogleJsonClientRequest<T> setDisableGZipContent(final boolean disableGZipContent) {
        return (MockGoogleJsonClientRequest)super.setDisableGZipContent(disableGZipContent);
    }
    
    @Override
    public MockGoogleJsonClientRequest<T> setRequestHeaders(final HttpHeaders requestHeaders) {
        return (MockGoogleJsonClientRequest)super.setRequestHeaders(requestHeaders);
    }
    
    @Override
    public /* bridge */ AbstractGoogleJsonClientRequest setRequestHeaders(final HttpHeaders requestHeaders) {
        return this.setRequestHeaders(requestHeaders);
    }
    
    @Override
    public /* bridge */ AbstractGoogleJsonClientRequest setDisableGZipContent(final boolean disableGZipContent) {
        return this.setDisableGZipContent(disableGZipContent);
    }
    
    @Override
    public /* bridge */ AbstractGoogleJsonClient getAbstractGoogleClient() {
        return this.getAbstractGoogleClient();
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest setRequestHeaders(final HttpHeaders requestHeaders) {
        return this.setRequestHeaders(requestHeaders);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient getAbstractGoogleClient() {
        return this.getAbstractGoogleClient();
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest setDisableGZipContent(final boolean disableGZipContent) {
        return this.setDisableGZipContent(disableGZipContent);
    }
}
