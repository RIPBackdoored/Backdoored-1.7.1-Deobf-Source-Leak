package com.google.api.client.googleapis.testing.auth.oauth2;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.util.Clock;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

@Beta
public static class Builder extends GoogleCredential.Builder
{
    public Builder() {
        super();
    }
    
    @Override
    public Builder setTransport(final HttpTransport transport) {
        return (Builder)super.setTransport(transport);
    }
    
    @Override
    public Builder setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return (Builder)super.setClientAuthentication(clientAuthentication);
    }
    
    @Override
    public Builder setJsonFactory(final JsonFactory jsonFactory) {
        return (Builder)super.setJsonFactory(jsonFactory);
    }
    
    @Override
    public Builder setClock(final Clock clock) {
        return (Builder)super.setClock(clock);
    }
    
    @Override
    public MockGoogleCredential build() {
        if (this.getTransport() == null) {
            this.setTransport(new MockHttpTransport.Builder().build());
        }
        if (this.getClientAuthentication() == null) {
            this.setClientAuthentication(new MockClientAuthentication());
        }
        if (this.getJsonFactory() == null) {
            this.setJsonFactory(new JacksonFactory());
        }
        return new MockGoogleCredential(this);
    }
    
    @Override
    public /* bridge */ GoogleCredential.Builder setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return this.setClientAuthentication(clientAuthentication);
    }
    
    @Override
    public /* bridge */ GoogleCredential.Builder setClock(final Clock clock) {
        return this.setClock(clock);
    }
    
    @Override
    public /* bridge */ GoogleCredential.Builder setJsonFactory(final JsonFactory jsonFactory) {
        return this.setJsonFactory(jsonFactory);
    }
    
    @Override
    public /* bridge */ GoogleCredential.Builder setTransport(final HttpTransport transport) {
        return this.setTransport(transport);
    }
    
    @Override
    public /* bridge */ GoogleCredential build() {
        return this.build();
    }
    
    @Override
    public /* bridge */ Credential.Builder setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return this.setClientAuthentication(clientAuthentication);
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
