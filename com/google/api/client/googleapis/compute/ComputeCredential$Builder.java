package com.google.api.client.googleapis.compute;

import java.util.Collection;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.auth.oauth2.Credential;

@Beta
public static class Builder extends Credential.Builder
{
    public Builder(final HttpTransport transport, final JsonFactory jsonFactory) {
        super(BearerToken.authorizationHeaderAccessMethod());
        this.setTransport(transport);
        this.setJsonFactory(jsonFactory);
        this.setTokenServerEncodedUrl(ComputeCredential.TOKEN_SERVER_ENCODED_URL);
    }
    
    @Override
    public ComputeCredential build() {
        return new ComputeCredential(this);
    }
    
    @Override
    public Builder setTransport(final HttpTransport httpTransport) {
        return (Builder)super.setTransport(Preconditions.<HttpTransport>checkNotNull(httpTransport));
    }
    
    @Override
    public Builder setClock(final Clock clock) {
        return (Builder)super.setClock(clock);
    }
    
    @Override
    public Builder setJsonFactory(final JsonFactory jsonFactory) {
        return (Builder)super.setJsonFactory(Preconditions.<JsonFactory>checkNotNull(jsonFactory));
    }
    
    @Override
    public Builder setTokenServerUrl(final GenericUrl genericUrl) {
        return (Builder)super.setTokenServerUrl(Preconditions.<GenericUrl>checkNotNull(genericUrl));
    }
    
    @Override
    public Builder setTokenServerEncodedUrl(final String s) {
        return (Builder)super.setTokenServerEncodedUrl(Preconditions.<String>checkNotNull(s));
    }
    
    @Override
    public Builder setClientAuthentication(final HttpExecuteInterceptor httpExecuteInterceptor) {
        Preconditions.checkArgument(httpExecuteInterceptor == null);
        return this;
    }
    
    @Override
    public Builder setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return (Builder)super.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public Builder addRefreshListener(final CredentialRefreshListener credentialRefreshListener) {
        return (Builder)super.addRefreshListener(credentialRefreshListener);
    }
    
    @Override
    public Builder setRefreshListeners(final Collection<CredentialRefreshListener> refreshListeners) {
        return (Builder)super.setRefreshListeners(refreshListeners);
    }
    
    @Override
    public /* bridge */ Credential.Builder setRefreshListeners(final Collection refreshListeners) {
        return this.setRefreshListeners(refreshListeners);
    }
    
    @Override
    public /* bridge */ Credential.Builder addRefreshListener(final CredentialRefreshListener credentialRefreshListener) {
        return this.addRefreshListener(credentialRefreshListener);
    }
    
    @Override
    public /* bridge */ Credential.Builder setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return this.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public /* bridge */ Credential.Builder setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return this.setClientAuthentication(clientAuthentication);
    }
    
    @Override
    public /* bridge */ Credential.Builder setTokenServerEncodedUrl(final String tokenServerEncodedUrl) {
        return this.setTokenServerEncodedUrl(tokenServerEncodedUrl);
    }
    
    @Override
    public /* bridge */ Credential.Builder setTokenServerUrl(final GenericUrl tokenServerUrl) {
        return this.setTokenServerUrl(tokenServerUrl);
    }
    
    @Override
    public /* bridge */ Credential.Builder setJsonFactory(final JsonFactory jsonFactory) {
        return this.setJsonFactory(jsonFactory);
    }
    
    @Override
    public /* bridge */ Credential.Builder setClock(final Clock clock) {
        return this.setClock(clock);
    }
    
    @Override
    public /* bridge */ Credential.Builder setTransport(final HttpTransport transport) {
        return this.setTransport(transport);
    }
    
    @Override
    public /* bridge */ Credential build() {
        return this.build();
    }
}
