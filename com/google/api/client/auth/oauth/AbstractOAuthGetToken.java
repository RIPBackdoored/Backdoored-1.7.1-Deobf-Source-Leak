package com.google.api.client.auth.oauth;

import java.io.IOException;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.UrlEncodedParser;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.http.GenericUrl;

@Beta
public abstract class AbstractOAuthGetToken extends GenericUrl
{
    public HttpTransport transport;
    public String consumerKey;
    public OAuthSigner signer;
    protected boolean usePost;
    
    protected AbstractOAuthGetToken(final String s) {
        super(s);
    }
    
    public final OAuthCredentialsResponse execute() throws IOException {
        final HttpRequest buildRequest = this.transport.createRequestFactory().buildRequest(this.usePost ? "POST" : "GET", this, null);
        this.createParameters().intercept(buildRequest);
        final HttpResponse execute = buildRequest.execute();
        execute.setContentLoggingLimit(0);
        final OAuthCredentialsResponse oAuthCredentialsResponse = new OAuthCredentialsResponse();
        UrlEncodedParser.parse(execute.parseAsString(), oAuthCredentialsResponse);
        return oAuthCredentialsResponse;
    }
    
    public OAuthParameters createParameters() {
        final OAuthParameters oAuthParameters = new OAuthParameters();
        oAuthParameters.consumerKey = this.consumerKey;
        oAuthParameters.signer = this.signer;
        return oAuthParameters;
    }
}
