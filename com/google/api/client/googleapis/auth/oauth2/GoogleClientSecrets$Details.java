package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.util.GenericData;
import java.util.List;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public static final class Details extends GenericJson
{
    @Key("client_id")
    private String clientId;
    @Key("client_secret")
    private String clientSecret;
    @Key("redirect_uris")
    private List<String> redirectUris;
    @Key("auth_uri")
    private String authUri;
    @Key("token_uri")
    private String tokenUri;
    
    public Details() {
        super();
    }
    
    public String getClientId() {
        return this.clientId;
    }
    
    public Details setClientId(final String clientId) {
        this.clientId = clientId;
        return this;
    }
    
    public String getClientSecret() {
        return this.clientSecret;
    }
    
    public Details setClientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }
    
    public List<String> getRedirectUris() {
        return this.redirectUris;
    }
    
    public Details setRedirectUris(final List<String> redirectUris) {
        this.redirectUris = redirectUris;
        return this;
    }
    
    public String getAuthUri() {
        return this.authUri;
    }
    
    public Details setAuthUri(final String authUri) {
        this.authUri = authUri;
        return this;
    }
    
    public String getTokenUri() {
        return this.tokenUri;
    }
    
    public Details setTokenUri(final String tokenUri) {
        this.tokenUri = tokenUri;
        return this;
    }
    
    @Override
    public Details set(final String s, final Object o) {
        return (Details)super.set(s, o);
    }
    
    @Override
    public Details clone() {
        return (Details)super.clone();
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
