package com.google.api.client.auth.oauth;

import com.google.api.client.util.Beta;

@Beta
public class OAuthGetTemporaryToken extends AbstractOAuthGetToken
{
    public String callback;
    
    public OAuthGetTemporaryToken(final String s) {
        super(s);
    }
    
    @Override
    public OAuthParameters createParameters() {
        final OAuthParameters parameters = super.createParameters();
        parameters.callback = this.callback;
        return parameters;
    }
}
