package com.google.api.client.auth.oauth2;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Lists;
import java.util.Collection;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.util.Clock;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;

public static class Builder
{
    final AccessMethod method;
    HttpTransport transport;
    JsonFactory jsonFactory;
    GenericUrl tokenServerUrl;
    Clock clock;
    HttpExecuteInterceptor clientAuthentication;
    HttpRequestInitializer requestInitializer;
    Collection<CredentialRefreshListener> refreshListeners;
    
    public Builder(final AccessMethod accessMethod) {
        super();
        this.clock = Clock.SYSTEM;
        this.refreshListeners = (Collection<CredentialRefreshListener>)Lists.<Object>newArrayList();
        this.method = Preconditions.<AccessMethod>checkNotNull(accessMethod);
    }
    
    public Credential build() {
        return new Credential(this);
    }
    
    public final AccessMethod getMethod() {
        return this.method;
    }
    
    public final HttpTransport getTransport() {
        return this.transport;
    }
    
    public Builder setTransport(final HttpTransport transport) {
        this.transport = transport;
        return this;
    }
    
    public final Clock getClock() {
        return this.clock;
    }
    
    public Builder setClock(final Clock clock) {
        this.clock = Preconditions.<Clock>checkNotNull(clock);
        return this;
    }
    
    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }
    
    public Builder setJsonFactory(final JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
        return this;
    }
    
    public final GenericUrl getTokenServerUrl() {
        return this.tokenServerUrl;
    }
    
    public Builder setTokenServerUrl(final GenericUrl tokenServerUrl) {
        this.tokenServerUrl = tokenServerUrl;
        return this;
    }
    
    public Builder setTokenServerEncodedUrl(final String s) {
        this.tokenServerUrl = ((s == null) ? null : new GenericUrl(s));
        return this;
    }
    
    public final HttpExecuteInterceptor getClientAuthentication() {
        return this.clientAuthentication;
    }
    
    public Builder setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        this.clientAuthentication = clientAuthentication;
        return this;
    }
    
    public final HttpRequestInitializer getRequestInitializer() {
        return this.requestInitializer;
    }
    
    public Builder setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        this.requestInitializer = requestInitializer;
        return this;
    }
    
    public Builder addRefreshListener(final CredentialRefreshListener credentialRefreshListener) {
        this.refreshListeners.add(Preconditions.<CredentialRefreshListener>checkNotNull(credentialRefreshListener));
        return this;
    }
    
    public final Collection<CredentialRefreshListener> getRefreshListeners() {
        return this.refreshListeners;
    }
    
    public Builder setRefreshListeners(final Collection<CredentialRefreshListener> collection) {
        this.refreshListeners = Preconditions.<Collection<CredentialRefreshListener>>checkNotNull(collection);
        return this;
    }
}
