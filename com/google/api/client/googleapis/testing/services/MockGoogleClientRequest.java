package com.google.api.client.googleapis.testing.services;

import com.google.api.client.util.GenericData;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpContent;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.util.Beta;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;

@Beta
public class MockGoogleClientRequest<T> extends AbstractGoogleClientRequest<T>
{
    public MockGoogleClientRequest(final AbstractGoogleClient abstractGoogleClient, final String s, final String s2, final HttpContent httpContent, final Class<T> clazz) {
        super(abstractGoogleClient, s, s2, httpContent, clazz);
    }
    
    @Override
    public MockGoogleClientRequest<T> setDisableGZipContent(final boolean disableGZipContent) {
        return (MockGoogleClientRequest)super.setDisableGZipContent(disableGZipContent);
    }
    
    @Override
    public MockGoogleClientRequest<T> setRequestHeaders(final HttpHeaders requestHeaders) {
        return (MockGoogleClientRequest)super.setRequestHeaders(requestHeaders);
    }
    
    @Override
    public MockGoogleClientRequest<T> set(final String s, final Object o) {
        return (MockGoogleClientRequest)super.set(s, o);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest setRequestHeaders(final HttpHeaders requestHeaders) {
        return this.setRequestHeaders(requestHeaders);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest setDisableGZipContent(final boolean disableGZipContent) {
        return this.setDisableGZipContent(disableGZipContent);
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
}
