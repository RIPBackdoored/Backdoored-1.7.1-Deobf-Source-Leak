package com.google.api.client.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.HttpExecuteInterceptor;
import java.util.Collection;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Key;

public class RefreshTokenRequest extends TokenRequest
{
    @Key("refresh_token")
    private String refreshToken;
    
    public RefreshTokenRequest(final HttpTransport httpTransport, final JsonFactory jsonFactory, final GenericUrl genericUrl, final String refreshToken) {
        super(httpTransport, jsonFactory, genericUrl, "refresh_token");
        this.setRefreshToken(refreshToken);
    }
    
    @Override
    public RefreshTokenRequest setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return (RefreshTokenRequest)super.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public RefreshTokenRequest setTokenServerUrl(final GenericUrl tokenServerUrl) {
        return (RefreshTokenRequest)super.setTokenServerUrl(tokenServerUrl);
    }
    
    @Override
    public RefreshTokenRequest setScopes(final Collection<String> scopes) {
        return (RefreshTokenRequest)super.setScopes(scopes);
    }
    
    @Override
    public RefreshTokenRequest setGrantType(final String grantType) {
        return (RefreshTokenRequest)super.setGrantType(grantType);
    }
    
    @Override
    public RefreshTokenRequest setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return (RefreshTokenRequest)super.setClientAuthentication(clientAuthentication);
    }
    
    public final String getRefreshToken() {
        return this.refreshToken;
    }
    
    public RefreshTokenRequest setRefreshToken(final String s) {
        this.refreshToken = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    @Override
    public RefreshTokenRequest set(final String s, final Object o) {
        return (RefreshTokenRequest)super.set(s, o);
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
