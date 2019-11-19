package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.auth.oauth2.TokenRequest;
import java.io.IOException;
import com.google.api.client.util.Preconditions;
import java.util.Collection;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;

public class GoogleAuthorizationCodeTokenRequest extends AuthorizationCodeTokenRequest
{
    public GoogleAuthorizationCodeTokenRequest(final HttpTransport httpTransport, final JsonFactory jsonFactory, final String s, final String s2, final String s3, final String s4) {
        this(httpTransport, jsonFactory, "https://accounts.google.com/o/oauth2/token", s, s2, s3, s4);
    }
    
    public GoogleAuthorizationCodeTokenRequest(final HttpTransport httpTransport, final JsonFactory jsonFactory, final String s, final String s2, final String s3, final String s4, final String redirectUri) {
        super(httpTransport, jsonFactory, new GenericUrl(s), s4);
        this.setClientAuthentication(new ClientParametersAuthentication(s2, s3));
        this.setRedirectUri(redirectUri);
    }
    
    @Override
    public GoogleAuthorizationCodeTokenRequest setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return (GoogleAuthorizationCodeTokenRequest)super.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public GoogleAuthorizationCodeTokenRequest setTokenServerUrl(final GenericUrl tokenServerUrl) {
        return (GoogleAuthorizationCodeTokenRequest)super.setTokenServerUrl(tokenServerUrl);
    }
    
    @Override
    public GoogleAuthorizationCodeTokenRequest setScopes(final Collection<String> scopes) {
        return (GoogleAuthorizationCodeTokenRequest)super.setScopes(scopes);
    }
    
    @Override
    public GoogleAuthorizationCodeTokenRequest setGrantType(final String grantType) {
        return (GoogleAuthorizationCodeTokenRequest)super.setGrantType(grantType);
    }
    
    @Override
    public GoogleAuthorizationCodeTokenRequest setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        Preconditions.<HttpExecuteInterceptor>checkNotNull(clientAuthentication);
        return (GoogleAuthorizationCodeTokenRequest)super.setClientAuthentication(clientAuthentication);
    }
    
    @Override
    public GoogleAuthorizationCodeTokenRequest setCode(final String code) {
        return (GoogleAuthorizationCodeTokenRequest)super.setCode(code);
    }
    
    @Override
    public GoogleAuthorizationCodeTokenRequest setRedirectUri(final String redirectUri) {
        Preconditions.<String>checkNotNull(redirectUri);
        return (GoogleAuthorizationCodeTokenRequest)super.setRedirectUri(redirectUri);
    }
    
    @Override
    public GoogleTokenResponse execute() throws IOException {
        return this.executeUnparsed().<GoogleTokenResponse>parseAs(GoogleTokenResponse.class);
    }
    
    @Override
    public GoogleAuthorizationCodeTokenRequest set(final String s, final Object o) {
        return (GoogleAuthorizationCodeTokenRequest)super.set(s, o);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeTokenRequest set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeTokenRequest setRedirectUri(final String redirectUri) {
        return this.setRedirectUri(redirectUri);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeTokenRequest setCode(final String code) {
        return this.setCode(code);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeTokenRequest setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return this.setClientAuthentication(clientAuthentication);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeTokenRequest setGrantType(final String grantType) {
        return this.setGrantType(grantType);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeTokenRequest setScopes(final Collection scopes) {
        return this.setScopes(scopes);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeTokenRequest setTokenServerUrl(final GenericUrl tokenServerUrl) {
        return this.setTokenServerUrl(tokenServerUrl);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeTokenRequest setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return this.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public /* bridge */ TokenRequest set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ TokenResponse execute() throws IOException {
        return this.execute();
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
