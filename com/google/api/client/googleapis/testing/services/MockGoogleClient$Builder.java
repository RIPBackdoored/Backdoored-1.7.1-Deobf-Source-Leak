package com.google.api.client.googleapis.testing.services;

import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.googleapis.services.AbstractGoogleClient;

@Beta
public static class Builder extends AbstractGoogleClient.Builder
{
    public Builder(final HttpTransport httpTransport, final String s, final String s2, final ObjectParser objectParser, final HttpRequestInitializer httpRequestInitializer) {
        super(httpTransport, s, s2, objectParser, httpRequestInitializer);
    }
    
    @Override
    public MockGoogleClient build() {
        return new MockGoogleClient(this);
    }
    
    @Override
    public Builder setRootUrl(final String rootUrl) {
        return (Builder)super.setRootUrl(rootUrl);
    }
    
    @Override
    public Builder setServicePath(final String servicePath) {
        return (Builder)super.setServicePath(servicePath);
    }
    
    @Override
    public Builder setGoogleClientRequestInitializer(final GoogleClientRequestInitializer googleClientRequestInitializer) {
        return (Builder)super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
    
    @Override
    public Builder setHttpRequestInitializer(final HttpRequestInitializer httpRequestInitializer) {
        return (Builder)super.setHttpRequestInitializer(httpRequestInitializer);
    }
    
    @Override
    public Builder setApplicationName(final String applicationName) {
        return (Builder)super.setApplicationName(applicationName);
    }
    
    @Override
    public Builder setSuppressPatternChecks(final boolean suppressPatternChecks) {
        return (Builder)super.setSuppressPatternChecks(suppressPatternChecks);
    }
    
    @Override
    public Builder setSuppressRequiredParameterChecks(final boolean suppressRequiredParameterChecks) {
        return (Builder)super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }
    
    @Override
    public Builder setSuppressAllChecks(final boolean suppressAllChecks) {
        return (Builder)super.setSuppressAllChecks(suppressAllChecks);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient.Builder setSuppressAllChecks(final boolean suppressAllChecks) {
        return this.setSuppressAllChecks(suppressAllChecks);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient.Builder setSuppressRequiredParameterChecks(final boolean suppressRequiredParameterChecks) {
        return this.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient.Builder setSuppressPatternChecks(final boolean suppressPatternChecks) {
        return this.setSuppressPatternChecks(suppressPatternChecks);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient.Builder setApplicationName(final String applicationName) {
        return this.setApplicationName(applicationName);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient.Builder setHttpRequestInitializer(final HttpRequestInitializer httpRequestInitializer) {
        return this.setHttpRequestInitializer(httpRequestInitializer);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient.Builder setGoogleClientRequestInitializer(final GoogleClientRequestInitializer googleClientRequestInitializer) {
        return this.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient.Builder setServicePath(final String servicePath) {
        return this.setServicePath(servicePath);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient.Builder setRootUrl(final String rootUrl) {
        return this.setRootUrl(rootUrl);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient build() {
        return this.build();
    }
}
