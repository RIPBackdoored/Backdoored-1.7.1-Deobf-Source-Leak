package com.google.api.client.auth.oauth;

import com.google.api.client.util.Key;
import com.google.api.client.util.Beta;
import com.google.api.client.http.GenericUrl;

@Beta
public class OAuthCallbackUrl extends GenericUrl
{
    @Key("oauth_token")
    public String token;
    @Key("oauth_verifier")
    public String verifier;
    
    public OAuthCallbackUrl(final String s) {
        super(s);
    }
}
