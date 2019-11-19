package com.google.api.client.auth.oauth;

import com.google.api.client.util.Beta;

@Beta
public class OAuthGetAccessToken extends AbstractOAuthGetToken
{
    public String temporaryToken;
    public String verifier;
    
    public OAuthGetAccessToken(final String s) {
        super(s);
    }
    
    @Override
    public OAuthParameters createParameters() {
        final OAuthParameters parameters = super.createParameters();
        parameters.token = this.temporaryToken;
        parameters.verifier = this.verifier;
        return parameters;
    }
}
