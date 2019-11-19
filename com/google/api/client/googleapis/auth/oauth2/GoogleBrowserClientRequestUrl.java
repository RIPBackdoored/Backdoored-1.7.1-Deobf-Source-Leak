package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.auth.oauth2.AuthorizationRequestUrl;
import com.google.api.client.util.Preconditions;
import java.util.Collection;
import com.google.api.client.util.Key;
import com.google.api.client.auth.oauth2.BrowserClientRequestUrl;

public class GoogleBrowserClientRequestUrl extends BrowserClientRequestUrl
{
    @Key("approval_prompt")
    private String approvalPrompt;
    
    public GoogleBrowserClientRequestUrl(final String s, final String redirectUri, final Collection<String> scopes) {
        super("https://accounts.google.com/o/oauth2/auth", s);
        this.setRedirectUri(redirectUri);
        this.setScopes(scopes);
    }
    
    public GoogleBrowserClientRequestUrl(final GoogleClientSecrets googleClientSecrets, final String s, final Collection<String> collection) {
        this(googleClientSecrets.getDetails().getClientId(), s, collection);
    }
    
    public final String getApprovalPrompt() {
        return this.approvalPrompt;
    }
    
    public GoogleBrowserClientRequestUrl setApprovalPrompt(final String approvalPrompt) {
        this.approvalPrompt = approvalPrompt;
        return this;
    }
    
    @Override
    public GoogleBrowserClientRequestUrl setResponseTypes(final Collection<String> responseTypes) {
        return (GoogleBrowserClientRequestUrl)super.setResponseTypes(responseTypes);
    }
    
    @Override
    public GoogleBrowserClientRequestUrl setRedirectUri(final String redirectUri) {
        return (GoogleBrowserClientRequestUrl)super.setRedirectUri(redirectUri);
    }
    
    @Override
    public GoogleBrowserClientRequestUrl setScopes(final Collection<String> scopes) {
        Preconditions.checkArgument(scopes.iterator().hasNext());
        return (GoogleBrowserClientRequestUrl)super.setScopes(scopes);
    }
    
    @Override
    public GoogleBrowserClientRequestUrl setClientId(final String clientId) {
        return (GoogleBrowserClientRequestUrl)super.setClientId(clientId);
    }
    
    @Override
    public GoogleBrowserClientRequestUrl setState(final String state) {
        return (GoogleBrowserClientRequestUrl)super.setState(state);
    }
    
    @Override
    public GoogleBrowserClientRequestUrl set(final String s, final Object o) {
        return (GoogleBrowserClientRequestUrl)super.set(s, o);
    }
    
    @Override
    public GoogleBrowserClientRequestUrl clone() {
        return (GoogleBrowserClientRequestUrl)super.clone();
    }
    
    @Override
    public /* bridge */ BrowserClientRequestUrl clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ BrowserClientRequestUrl set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ BrowserClientRequestUrl setState(final String state) {
        return this.setState(state);
    }
    
    @Override
    public /* bridge */ BrowserClientRequestUrl setClientId(final String clientId) {
        return this.setClientId(clientId);
    }
    
    @Override
    public /* bridge */ BrowserClientRequestUrl setScopes(final Collection scopes) {
        return this.setScopes(scopes);
    }
    
    @Override
    public /* bridge */ BrowserClientRequestUrl setRedirectUri(final String redirectUri) {
        return this.setRedirectUri(redirectUri);
    }
    
    @Override
    public /* bridge */ BrowserClientRequestUrl setResponseTypes(final Collection responseTypes) {
        return this.setResponseTypes(responseTypes);
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
