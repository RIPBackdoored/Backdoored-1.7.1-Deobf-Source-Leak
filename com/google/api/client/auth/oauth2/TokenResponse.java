package com.google.api.client.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public class TokenResponse extends GenericJson
{
    @Key("access_token")
    private String accessToken;
    @Key("token_type")
    private String tokenType;
    @Key("expires_in")
    private Long expiresInSeconds;
    @Key("refresh_token")
    private String refreshToken;
    @Key
    private String scope;
    
    public TokenResponse() {
        super();
    }
    
    public final String getAccessToken() {
        return this.accessToken;
    }
    
    public TokenResponse setAccessToken(final String s) {
        this.accessToken = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final String getTokenType() {
        return this.tokenType;
    }
    
    public TokenResponse setTokenType(final String s) {
        this.tokenType = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final Long getExpiresInSeconds() {
        return this.expiresInSeconds;
    }
    
    public TokenResponse setExpiresInSeconds(final Long expiresInSeconds) {
        this.expiresInSeconds = expiresInSeconds;
        return this;
    }
    
    public final String getRefreshToken() {
        return this.refreshToken;
    }
    
    public TokenResponse setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
    
    public final String getScope() {
        return this.scope;
    }
    
    public TokenResponse setScope(final String scope) {
        this.scope = scope;
        return this;
    }
    
    @Override
    public TokenResponse set(final String s, final Object o) {
        return (TokenResponse)super.set(s, o);
    }
    
    @Override
    public TokenResponse clone() {
        return (TokenResponse)super.clone();
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
