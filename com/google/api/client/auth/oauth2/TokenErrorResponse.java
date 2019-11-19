package com.google.api.client.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public class TokenErrorResponse extends GenericJson
{
    @Key
    private String error;
    @Key("error_description")
    private String errorDescription;
    @Key("error_uri")
    private String errorUri;
    
    public TokenErrorResponse() {
        super();
    }
    
    public final String getError() {
        return this.error;
    }
    
    public TokenErrorResponse setError(final String s) {
        this.error = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final String getErrorDescription() {
        return this.errorDescription;
    }
    
    public TokenErrorResponse setErrorDescription(final String errorDescription) {
        this.errorDescription = errorDescription;
        return this;
    }
    
    public final String getErrorUri() {
        return this.errorUri;
    }
    
    public TokenErrorResponse setErrorUri(final String errorUri) {
        this.errorUri = errorUri;
        return this;
    }
    
    @Override
    public TokenErrorResponse set(final String s, final Object o) {
        return (TokenErrorResponse)super.set(s, o);
    }
    
    @Override
    public TokenErrorResponse clone() {
        return (TokenErrorResponse)super.clone();
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
