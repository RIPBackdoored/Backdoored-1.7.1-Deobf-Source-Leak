package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.auth.oauth2.AuthorizationRequestUrl;
import com.google.api.client.util.Preconditions;
import java.util.Collection;
import com.google.api.client.util.Key;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;

public class GoogleAuthorizationCodeRequestUrl extends AuthorizationCodeRequestUrl
{
    @Key("approval_prompt")
    private String approvalPrompt;
    @Key("access_type")
    private String accessType;
    
    public GoogleAuthorizationCodeRequestUrl(final String s, final String s2, final Collection<String> collection) {
        this("https://accounts.google.com/o/oauth2/auth", s, s2, collection);
    }
    
    public GoogleAuthorizationCodeRequestUrl(final String s, final String s2, final String redirectUri, final Collection<String> scopes) {
        super(s, s2);
        this.setRedirectUri(redirectUri);
        this.setScopes(scopes);
    }
    
    public GoogleAuthorizationCodeRequestUrl(final GoogleClientSecrets googleClientSecrets, final String s, final Collection<String> collection) {
        this(googleClientSecrets.getDetails().getClientId(), s, collection);
    }
    
    public final String getApprovalPrompt() {
        return this.approvalPrompt;
    }
    
    public GoogleAuthorizationCodeRequestUrl setApprovalPrompt(final String approvalPrompt) {
        this.approvalPrompt = approvalPrompt;
        return this;
    }
    
    public final String getAccessType() {
        return this.accessType;
    }
    
    public GoogleAuthorizationCodeRequestUrl setAccessType(final String accessType) {
        this.accessType = accessType;
        return this;
    }
    
    @Override
    public GoogleAuthorizationCodeRequestUrl setResponseTypes(final Collection<String> responseTypes) {
        return (GoogleAuthorizationCodeRequestUrl)super.setResponseTypes(responseTypes);
    }
    
    @Override
    public GoogleAuthorizationCodeRequestUrl setRedirectUri(final String redirectUri) {
        Preconditions.<String>checkNotNull(redirectUri);
        return (GoogleAuthorizationCodeRequestUrl)super.setRedirectUri(redirectUri);
    }
    
    @Override
    public GoogleAuthorizationCodeRequestUrl setScopes(final Collection<String> scopes) {
        Preconditions.checkArgument(scopes.iterator().hasNext());
        return (GoogleAuthorizationCodeRequestUrl)super.setScopes(scopes);
    }
    
    @Override
    public GoogleAuthorizationCodeRequestUrl setClientId(final String clientId) {
        return (GoogleAuthorizationCodeRequestUrl)super.setClientId(clientId);
    }
    
    @Override
    public GoogleAuthorizationCodeRequestUrl setState(final String state) {
        return (GoogleAuthorizationCodeRequestUrl)super.setState(state);
    }
    
    @Override
    public GoogleAuthorizationCodeRequestUrl set(final String s, final Object o) {
        return (GoogleAuthorizationCodeRequestUrl)super.set(s, o);
    }
    
    @Override
    public GoogleAuthorizationCodeRequestUrl clone() {
        return (GoogleAuthorizationCodeRequestUrl)super.clone();
    }
    
    @Override
    public /* bridge */ AuthorizationCodeRequestUrl clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ AuthorizationCodeRequestUrl set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeRequestUrl setState(final String state) {
        return this.setState(state);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeRequestUrl setClientId(final String clientId) {
        return this.setClientId(clientId);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeRequestUrl setScopes(final Collection scopes) {
        return this.setScopes(scopes);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeRequestUrl setRedirectUri(final String redirectUri) {
        return this.setRedirectUri(redirectUri);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeRequestUrl setResponseTypes(final Collection responseTypes) {
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
