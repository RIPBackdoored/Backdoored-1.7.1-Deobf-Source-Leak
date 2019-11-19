package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.json.GenericJson;
import java.io.IOException;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import com.google.api.client.auth.oauth2.TokenResponse;

public class GoogleTokenResponse extends TokenResponse
{
    @Key("id_token")
    private String idToken;
    
    public GoogleTokenResponse() {
        super();
    }
    
    @Override
    public GoogleTokenResponse setAccessToken(final String accessToken) {
        return (GoogleTokenResponse)super.setAccessToken(accessToken);
    }
    
    @Override
    public GoogleTokenResponse setTokenType(final String tokenType) {
        return (GoogleTokenResponse)super.setTokenType(tokenType);
    }
    
    @Override
    public GoogleTokenResponse setExpiresInSeconds(final Long expiresInSeconds) {
        return (GoogleTokenResponse)super.setExpiresInSeconds(expiresInSeconds);
    }
    
    @Override
    public GoogleTokenResponse setRefreshToken(final String refreshToken) {
        return (GoogleTokenResponse)super.setRefreshToken(refreshToken);
    }
    
    @Override
    public GoogleTokenResponse setScope(final String scope) {
        return (GoogleTokenResponse)super.setScope(scope);
    }
    
    @Beta
    @Beta
    public final String getIdToken() {
        return this.idToken;
    }
    
    @Beta
    @Beta
    public GoogleTokenResponse setIdToken(final String s) {
        this.idToken = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    @Beta
    @Beta
    public GoogleIdToken parseIdToken() throws IOException {
        return GoogleIdToken.parse(this.getFactory(), this.getIdToken());
    }
    
    @Override
    public GoogleTokenResponse set(final String s, final Object o) {
        return (GoogleTokenResponse)super.set(s, o);
    }
    
    @Override
    public GoogleTokenResponse clone() {
        return (GoogleTokenResponse)super.clone();
    }
    
    @Override
    public /* bridge */ TokenResponse clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ TokenResponse set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ TokenResponse setScope(final String scope) {
        return this.setScope(scope);
    }
    
    @Override
    public /* bridge */ TokenResponse setRefreshToken(final String refreshToken) {
        return this.setRefreshToken(refreshToken);
    }
    
    @Override
    public /* bridge */ TokenResponse setExpiresInSeconds(final Long expiresInSeconds) {
        return this.setExpiresInSeconds(expiresInSeconds);
    }
    
    @Override
    public /* bridge */ TokenResponse setTokenType(final String tokenType) {
        return this.setTokenType(tokenType);
    }
    
    @Override
    public /* bridge */ TokenResponse setAccessToken(final String accessToken) {
        return this.setAccessToken(accessToken);
    }
    
    @Override
    public /* bridge */ GenericJson set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ GenericJson clone() {
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
