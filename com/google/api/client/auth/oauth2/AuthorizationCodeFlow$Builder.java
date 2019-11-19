package com.google.api.client.auth.oauth2;

import java.io.IOException;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Clock;
import java.util.Collection;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.Beta;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;

public static class Builder
{
    Credential.AccessMethod method;
    HttpTransport transport;
    JsonFactory jsonFactory;
    GenericUrl tokenServerUrl;
    HttpExecuteInterceptor clientAuthentication;
    String clientId;
    String authorizationServerEncodedUrl;
    @Deprecated
    @Beta
    CredentialStore credentialStore;
    @Beta
    DataStore<StoredCredential> credentialDataStore;
    HttpRequestInitializer requestInitializer;
    Collection<String> scopes;
    Clock clock;
    CredentialCreatedListener credentialCreatedListener;
    Collection<CredentialRefreshListener> refreshListeners;
    
    public Builder(final Credential.AccessMethod method, final HttpTransport transport, final JsonFactory jsonFactory, final GenericUrl tokenServerUrl, final HttpExecuteInterceptor clientAuthentication, final String clientId, final String authorizationServerEncodedUrl) {
        super();
        this.scopes = (Collection<String>)Lists.<Object>newArrayList();
        this.clock = Clock.SYSTEM;
        this.refreshListeners = (Collection<CredentialRefreshListener>)Lists.<Object>newArrayList();
        this.setMethod(method);
        this.setTransport(transport);
        this.setJsonFactory(jsonFactory);
        this.setTokenServerUrl(tokenServerUrl);
        this.setClientAuthentication(clientAuthentication);
        this.setClientId(clientId);
        this.setAuthorizationServerEncodedUrl(authorizationServerEncodedUrl);
    }
    
    public AuthorizationCodeFlow build() {
        return new AuthorizationCodeFlow(this);
    }
    
    public final Credential.AccessMethod getMethod() {
        return this.method;
    }
    
    public Builder setMethod(final Credential.AccessMethod accessMethod) {
        this.method = Preconditions.<Credential.AccessMethod>checkNotNull(accessMethod);
        return this;
    }
    
    public final HttpTransport getTransport() {
        return this.transport;
    }
    
    public Builder setTransport(final HttpTransport httpTransport) {
        this.transport = Preconditions.<HttpTransport>checkNotNull(httpTransport);
        return this;
    }
    
    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }
    
    public Builder setJsonFactory(final JsonFactory jsonFactory) {
        this.jsonFactory = Preconditions.<JsonFactory>checkNotNull(jsonFactory);
        return this;
    }
    
    public final GenericUrl getTokenServerUrl() {
        return this.tokenServerUrl;
    }
    
    public Builder setTokenServerUrl(final GenericUrl genericUrl) {
        this.tokenServerUrl = Preconditions.<GenericUrl>checkNotNull(genericUrl);
        return this;
    }
    
    public final HttpExecuteInterceptor getClientAuthentication() {
        return this.clientAuthentication;
    }
    
    public Builder setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        this.clientAuthentication = clientAuthentication;
        return this;
    }
    
    public final String getClientId() {
        return this.clientId;
    }
    
    public Builder setClientId(final String s) {
        this.clientId = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final String getAuthorizationServerEncodedUrl() {
        return this.authorizationServerEncodedUrl;
    }
    
    public Builder setAuthorizationServerEncodedUrl(final String s) {
        this.authorizationServerEncodedUrl = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    @Deprecated
    @Beta
    public final CredentialStore getCredentialStore() {
        return this.credentialStore;
    }
    
    @Beta
    public final DataStore<StoredCredential> getCredentialDataStore() {
        return this.credentialDataStore;
    }
    
    public final Clock getClock() {
        return this.clock;
    }
    
    public Builder setClock(final Clock clock) {
        this.clock = Preconditions.<Clock>checkNotNull(clock);
        return this;
    }
    
    @Deprecated
    @Beta
    public Builder setCredentialStore(final CredentialStore credentialStore) {
        Preconditions.checkArgument(this.credentialDataStore == null);
        this.credentialStore = credentialStore;
        return this;
    }
    
    @Beta
    public Builder setDataStoreFactory(final DataStoreFactory dataStoreFactory) throws IOException {
        return this.setCredentialDataStore(StoredCredential.getDefaultDataStore(dataStoreFactory));
    }
    
    @Beta
    public Builder setCredentialDataStore(final DataStore<StoredCredential> credentialDataStore) {
        Preconditions.checkArgument(this.credentialStore == null);
        this.credentialDataStore = credentialDataStore;
        return this;
    }
    
    public final HttpRequestInitializer getRequestInitializer() {
        return this.requestInitializer;
    }
    
    public Builder setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        this.requestInitializer = requestInitializer;
        return this;
    }
    
    public Builder setScopes(final Collection<String> collection) {
        this.scopes = Preconditions.<Collection<String>>checkNotNull(collection);
        return this;
    }
    
    public final Collection<String> getScopes() {
        return this.scopes;
    }
    
    public Builder setCredentialCreatedListener(final CredentialCreatedListener credentialCreatedListener) {
        this.credentialCreatedListener = credentialCreatedListener;
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
    
    public final CredentialCreatedListener getCredentialCreatedListener() {
        return this.credentialCreatedListener;
    }
}
