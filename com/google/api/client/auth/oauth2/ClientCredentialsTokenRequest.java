package com.google.api.client.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.http.HttpExecuteInterceptor;
import java.util.Collection;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;

public class ClientCredentialsTokenRequest extends TokenRequest
{
    public ClientCredentialsTokenRequest(final HttpTransport httpTransport, final JsonFactory jsonFactory, final GenericUrl genericUrl) {
        super(httpTransport, jsonFactory, genericUrl, "client_credentials");
    }
    
    @Override
    public ClientCredentialsTokenRequest setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return (ClientCredentialsTokenRequest)super.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public ClientCredentialsTokenRequest setTokenServerUrl(final GenericUrl tokenServerUrl) {
        return (ClientCredentialsTokenRequest)super.setTokenServerUrl(tokenServerUrl);
    }
    
    @Override
    public ClientCredentialsTokenRequest setScopes(final Collection<String> scopes) {
        return (ClientCredentialsTokenRequest)super.setScopes(scopes);
    }
    
    @Override
    public ClientCredentialsTokenRequest setGrantType(final String grantType) {
        return (ClientCredentialsTokenRequest)super.setGrantType(grantType);
    }
    
    @Override
    public ClientCredentialsTokenRequest setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return (ClientCredentialsTokenRequest)super.setClientAuthentication(clientAuthentication);
    }
    
    @Override
    public ClientCredentialsTokenRequest set(final String s, final Object o) {
        return (ClientCredentialsTokenRequest)super.set(s, o);
    }
    
    @Override
    public /* bridge */ TokenRequest set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ TokenRequest setGrantType(final String grantType) {
        return this.setGrantType(grantType);
    }
    
    @Override
    public /* bridge */ TokenRequest setScopes(final Collection scopes) {
        return this.setScopes(scopes);
    }
    
    @Override
    public /* bridge */ TokenRequest setTokenServerUrl(final GenericUrl tokenServerUrl) {
        return this.setTokenServerUrl(tokenServerUrl);
    }
    
    @Override
    public /* bridge */ TokenRequest setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return this.setClientAuthentication(clientAuthentication);
    }
    
    @Override
    public /* bridge */ TokenRequest setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return this.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
}
