package com.google.api.client.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.http.GenericUrl;
import java.util.Collection;
import java.util.Collections;

public class BrowserClientRequestUrl extends AuthorizationRequestUrl
{
    public BrowserClientRequestUrl(final String s, final String s2) {
        super(s, s2, Collections.<String>singleton("token"));
    }
    
    @Override
    public BrowserClientRequestUrl setResponseTypes(final Collection<String> responseTypes) {
        return (BrowserClientRequestUrl)super.setResponseTypes(responseTypes);
    }
    
    @Override
    public BrowserClientRequestUrl setRedirectUri(final String redirectUri) {
        return (BrowserClientRequestUrl)super.setRedirectUri(redirectUri);
    }
    
    @Override
    public BrowserClientRequestUrl setScopes(final Collection<String> scopes) {
        return (BrowserClientRequestUrl)super.setScopes(scopes);
    }
    
    @Override
    public BrowserClientRequestUrl setClientId(final String clientId) {
        return (BrowserClientRequestUrl)super.setClientId(clientId);
    }
    
    @Override
    public BrowserClientRequestUrl setState(final String state) {
        return (BrowserClientRequestUrl)super.setState(state);
    }
    
    @Override
    public BrowserClientRequestUrl set(final String s, final Object o) {
        return (BrowserClientRequestUrl)super.set(s, o);
    }
    
    @Override
    public BrowserClientRequestUrl clone() {
        return (BrowserClientRequestUrl)super.clone();
    }
    
    @Override
    public /* bridge */ AuthorizationRequestUrl clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ AuthorizationRequestUrl set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ AuthorizationRequestUrl setState(final String state) {
        return this.setState(state);
    }
    
    @Override
    public /* bridge */ AuthorizationRequestUrl setClientId(final String clientId) {
        return this.setClientId(clientId);
    }
    
    @Override
    public /* bridge */ AuthorizationRequestUrl setScopes(final Collection scopes) {
        return this.setScopes(scopes);
    }
    
    @Override
    public /* bridge */ AuthorizationRequestUrl setRedirectUri(final String redirectUri) {
        return this.setRedirectUri(redirectUri);
    }
    
    @Override
    public /* bridge */ AuthorizationRequestUrl setResponseTypes(final Collection responseTypes) {
        return this.setResponseTypes(responseTypes);
    }
    
    @Override
    public /* bridge */ GenericUrl set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ GenericUrl clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ GenericData clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    public /* bridge */ Object clone() throws CloneNotSupportedException {
        return this.clone();
    }
}
