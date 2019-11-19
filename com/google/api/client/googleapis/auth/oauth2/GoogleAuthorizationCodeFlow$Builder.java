package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.util.Clock;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.Beta;
import com.google.api.client.auth.oauth2.CredentialStore;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;
import java.io.IOException;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.auth.oauth2.BearerToken;
import java.util.Collection;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;

public static class Builder extends AuthorizationCodeFlow.Builder
{
    String approvalPrompt;
    String accessType;
    
    public Builder(final HttpTransport httpTransport, final JsonFactory jsonFactory, final String s, final String s2, final Collection<String> scopes) {
        super(BearerToken.authorizationHeaderAccessMethod(), httpTransport, jsonFactory, new GenericUrl("https://accounts.google.com/o/oauth2/token"), new ClientParametersAuthentication(s, s2), s, "https://accounts.google.com/o/oauth2/auth");
        this.setScopes(scopes);
    }
    
    public Builder(final HttpTransport httpTransport, final JsonFactory jsonFactory, final GoogleClientSecrets googleClientSecrets, final Collection<String> scopes) {
        super(BearerToken.authorizationHeaderAccessMethod(), httpTransport, jsonFactory, new GenericUrl("https://accounts.google.com/o/oauth2/token"), new ClientParametersAuthentication(googleClientSecrets.getDetails().getClientId(), googleClientSecrets.getDetails().getClientSecret()), googleClientSecrets.getDetails().getClientId(), "https://accounts.google.com/o/oauth2/auth");
        this.setScopes(scopes);
    }
    
    @Override
    public GoogleAuthorizationCodeFlow build() {
        return new GoogleAuthorizationCodeFlow(this);
    }
    
    @Override
    public Builder setDataStoreFactory(final DataStoreFactory dataStoreFactory) throws IOException {
        return (Builder)super.setDataStoreFactory(dataStoreFactory);
    }
    
    @Override
    public Builder setCredentialDataStore(final DataStore<StoredCredential> credentialDataStore) {
        return (Builder)super.setCredentialDataStore(credentialDataStore);
    }
    
    @Override
    public Builder setCredentialCreatedListener(final CredentialCreatedListener credentialCreatedListener) {
        return (Builder)super.setCredentialCreatedListener(credentialCreatedListener);
    }
    
    @Deprecated
    @Deprecated
    @Beta
    @Beta
    @Override
    public Builder setCredentialStore(final CredentialStore credentialStore) {
        return (Builder)super.setCredentialStore(credentialStore);
    }
    
    @Override
    public Builder setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return (Builder)super.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public Builder setScopes(final Collection<String> scopes) {
        Preconditions.checkState(!scopes.isEmpty());
        return (Builder)super.setScopes(scopes);
    }
    
    @Override
    public Builder setMethod(final Credential.AccessMethod method) {
        return (Builder)super.setMethod(method);
    }
    
    @Override
    public Builder setTransport(final HttpTransport transport) {
        return (Builder)super.setTransport(transport);
    }
    
    @Override
    public Builder setJsonFactory(final JsonFactory jsonFactory) {
        return (Builder)super.setJsonFactory(jsonFactory);
    }
    
    @Override
    public Builder setTokenServerUrl(final GenericUrl tokenServerUrl) {
        return (Builder)super.setTokenServerUrl(tokenServerUrl);
    }
    
    @Override
    public Builder setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return (Builder)super.setClientAuthentication(clientAuthentication);
    }
    
    @Override
    public Builder setClientId(final String clientId) {
        return (Builder)super.setClientId(clientId);
    }
    
    @Override
    public Builder setAuthorizationServerEncodedUrl(final String authorizationServerEncodedUrl) {
        return (Builder)super.setAuthorizationServerEncodedUrl(authorizationServerEncodedUrl);
    }
    
    @Override
    public Builder setClock(final Clock clock) {
        return (Builder)super.setClock(clock);
    }
    
    @Override
    public Builder addRefreshListener(final CredentialRefreshListener credentialRefreshListener) {
        return (Builder)super.addRefreshListener(credentialRefreshListener);
    }
    
    @Override
    public Builder setRefreshListeners(final Collection<CredentialRefreshListener> refreshListeners) {
        return (Builder)super.setRefreshListeners(refreshListeners);
    }
    
    public Builder setApprovalPrompt(final String approvalPrompt) {
        this.approvalPrompt = approvalPrompt;
        return this;
    }
    
    public final String getApprovalPrompt() {
        return this.approvalPrompt;
    }
    
    public Builder setAccessType(final String accessType) {
        this.accessType = accessType;
        return this;
    }
    
    public final String getAccessType() {
        return this.accessType;
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setRefreshListeners(final Collection refreshListeners) {
        return this.setRefreshListeners(refreshListeners);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder addRefreshListener(final CredentialRefreshListener credentialRefreshListener) {
        return this.addRefreshListener(credentialRefreshListener);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setCredentialCreatedListener(final CredentialCreatedListener credentialCreatedListener) {
        return this.setCredentialCreatedListener(credentialCreatedListener);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setScopes(final Collection scopes) {
        return this.setScopes(scopes);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return this.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setCredentialDataStore(final DataStore credentialDataStore) {
        return this.setCredentialDataStore(credentialDataStore);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setDataStoreFactory(final DataStoreFactory dataStoreFactory) throws IOException {
        return this.setDataStoreFactory(dataStoreFactory);
    }
    
    @Deprecated
    @Deprecated
    @Beta
    @Beta
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setCredentialStore(final CredentialStore credentialStore) {
        return this.setCredentialStore(credentialStore);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setClock(final Clock clock) {
        return this.setClock(clock);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setAuthorizationServerEncodedUrl(final String authorizationServerEncodedUrl) {
        return this.setAuthorizationServerEncodedUrl(authorizationServerEncodedUrl);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setClientId(final String clientId) {
        return this.setClientId(clientId);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return this.setClientAuthentication(clientAuthentication);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setTokenServerUrl(final GenericUrl tokenServerUrl) {
        return this.setTokenServerUrl(tokenServerUrl);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setJsonFactory(final JsonFactory jsonFactory) {
        return this.setJsonFactory(jsonFactory);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setTransport(final HttpTransport transport) {
        return this.setTransport(transport);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow.Builder setMethod(final Credential.AccessMethod method) {
        return this.setMethod(method);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeFlow build() {
        return this.build();
    }
}
