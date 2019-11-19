package com.google.api.client.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.Preconditions;
import java.util.Collection;
import com.google.api.client.util.Key;
import com.google.api.client.http.GenericUrl;

public class AuthorizationRequestUrl extends GenericUrl
{
    @Key("response_type")
    private String responseTypes;
    @Key("redirect_uri")
    private String redirectUri;
    @Key("scope")
    private String scopes;
    @Key("client_id")
    private String clientId;
    @Key
    private String state;
    
    public AuthorizationRequestUrl(final String s, final String clientId, final Collection<String> responseTypes) {
        super(s);
        Preconditions.checkArgument(this.getFragment() == null);
        this.setClientId(clientId);
        this.setResponseTypes(responseTypes);
    }
    
    public final String getResponseTypes() {
        return this.responseTypes;
    }
    
    public AuthorizationRequestUrl setResponseTypes(final Collection<String> collection) {
        this.responseTypes = Joiner.on(' ').join(collection);
        return this;
    }
    
    public final String getRedirectUri() {
        return this.redirectUri;
    }
    
    public AuthorizationRequestUrl setRedirectUri(final String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }
    
    public final String getScopes() {
        return this.scopes;
    }
    
    public AuthorizationRequestUrl setScopes(final Collection<String> collection) {
        this.scopes = ((collection == null || !collection.iterator().hasNext()) ? null : Joiner.on(' ').join(collection));
        return this;
    }
    
    public final String getClientId() {
        return this.clientId;
    }
    
    public AuthorizationRequestUrl setClientId(final String s) {
        this.clientId = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final String getState() {
        return this.state;
    }
    
    public AuthorizationRequestUrl setState(final String state) {
        this.state = state;
        return this;
    }
    
    @Override
    public AuthorizationRequestUrl set(final String s, final Object o) {
        return (AuthorizationRequestUrl)super.set(s, o);
    }
    
    @Override
    public AuthorizationRequestUrl clone() {
        return (AuthorizationRequestUrl)super.clone();
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
