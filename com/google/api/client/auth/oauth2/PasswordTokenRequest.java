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

public class PasswordTokenRequest extends TokenRequest
{
    @Key
    private String username;
    @Key
    private String password;
    
    public PasswordTokenRequest(final HttpTransport httpTransport, final JsonFactory jsonFactory, final GenericUrl genericUrl, final String username, final String password) {
        super(httpTransport, jsonFactory, genericUrl, "password");
        this.setUsername(username);
        this.setPassword(password);
    }
    
    @Override
    public PasswordTokenRequest setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return (PasswordTokenRequest)super.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public PasswordTokenRequest setTokenServerUrl(final GenericUrl tokenServerUrl) {
        return (PasswordTokenRequest)super.setTokenServerUrl(tokenServerUrl);
    }
    
    @Override
    public PasswordTokenRequest setScopes(final Collection<String> scopes) {
        return (PasswordTokenRequest)super.setScopes(scopes);
    }
    
    @Override
    public PasswordTokenRequest setGrantType(final String grantType) {
        return (PasswordTokenRequest)super.setGrantType(grantType);
    }
    
    @Override
    public PasswordTokenRequest setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return (PasswordTokenRequest)super.setClientAuthentication(clientAuthentication);
    }
    
    public final String getUsername() {
        return this.username;
    }
    
    public PasswordTokenRequest setUsername(final String s) {
        this.username = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final String getPassword() {
        return this.password;
    }
    
    public PasswordTokenRequest setPassword(final String s) {
        this.password = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    @Override
    public PasswordTokenRequest set(final String s, final Object o) {
        return (PasswordTokenRequest)super.set(s, o);
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
