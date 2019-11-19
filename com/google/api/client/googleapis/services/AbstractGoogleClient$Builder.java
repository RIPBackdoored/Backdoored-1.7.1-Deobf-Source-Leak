package com.google.api.client.googleapis.services;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;

public abstract static class Builder
{
    final HttpTransport transport;
    GoogleClientRequestInitializer googleClientRequestInitializer;
    HttpRequestInitializer httpRequestInitializer;
    final ObjectParser objectParser;
    String rootUrl;
    String servicePath;
    String batchPath;
    String applicationName;
    boolean suppressPatternChecks;
    boolean suppressRequiredParameterChecks;
    
    protected Builder(final HttpTransport httpTransport, final String rootUrl, final String servicePath, final ObjectParser objectParser, final HttpRequestInitializer httpRequestInitializer) {
        super();
        this.transport = Preconditions.<HttpTransport>checkNotNull(httpTransport);
        this.objectParser = objectParser;
        this.setRootUrl(rootUrl);
        this.setServicePath(servicePath);
        this.httpRequestInitializer = httpRequestInitializer;
    }
    
    public abstract AbstractGoogleClient build();
    
    public final HttpTransport getTransport() {
        return this.transport;
    }
    
    public ObjectParser getObjectParser() {
        return this.objectParser;
    }
    
    public final String getRootUrl() {
        return this.rootUrl;
    }
    
    public Builder setRootUrl(final String s) {
        this.rootUrl = AbstractGoogleClient.normalizeRootUrl(s);
        return this;
    }
    
    public final String getServicePath() {
        return this.servicePath;
    }
    
    public Builder setServicePath(final String s) {
        this.servicePath = AbstractGoogleClient.normalizeServicePath(s);
        return this;
    }
    
    public Builder setBatchPath(final String batchPath) {
        this.batchPath = batchPath;
        return this;
    }
    
    public final GoogleClientRequestInitializer getGoogleClientRequestInitializer() {
        return this.googleClientRequestInitializer;
    }
    
    public Builder setGoogleClientRequestInitializer(final GoogleClientRequestInitializer googleClientRequestInitializer) {
        this.googleClientRequestInitializer = googleClientRequestInitializer;
        return this;
    }
    
    public final HttpRequestInitializer getHttpRequestInitializer() {
        return this.httpRequestInitializer;
    }
    
    public Builder setHttpRequestInitializer(final HttpRequestInitializer httpRequestInitializer) {
        this.httpRequestInitializer = httpRequestInitializer;
        return this;
    }
    
    public final String getApplicationName() {
        return this.applicationName;
    }
    
    public Builder setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
        return this;
    }
    
    public final boolean getSuppressPatternChecks() {
        return this.suppressPatternChecks;
    }
    
    public Builder setSuppressPatternChecks(final boolean suppressPatternChecks) {
        this.suppressPatternChecks = suppressPatternChecks;
        return this;
    }
    
    public final boolean getSuppressRequiredParameterChecks() {
        return this.suppressRequiredParameterChecks;
    }
    
    public Builder setSuppressRequiredParameterChecks(final boolean suppressRequiredParameterChecks) {
        this.suppressRequiredParameterChecks = suppressRequiredParameterChecks;
        return this;
    }
    
    public Builder setSuppressAllChecks(final boolean b) {
        return this.setSuppressPatternChecks(true).setSuppressRequiredParameterChecks(true);
    }
}
