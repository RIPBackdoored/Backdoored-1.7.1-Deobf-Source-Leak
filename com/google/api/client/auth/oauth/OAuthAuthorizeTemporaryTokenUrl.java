package com.google.api.client.auth.oauth;

import com.google.api.client.util.Key;
import com.google.api.client.util.Beta;
import com.google.api.client.http.GenericUrl;

@Beta
public class OAuthAuthorizeTemporaryTokenUrl extends GenericUrl
{
    @Key("oauth_token")
    public String temporaryToken;
    
    public OAuthAuthorizeTemporaryTokenUrl(final String s) {
        super(s);
    }
}
