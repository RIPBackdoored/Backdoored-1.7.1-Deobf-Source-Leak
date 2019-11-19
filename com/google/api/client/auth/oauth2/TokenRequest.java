package com.google.api.client.auth.oauth2;

import com.google.api.client.util.ObjectParser;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.UrlEncodedContent;
import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Joiner;
import java.util.Collection;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Key;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.GenericData;

public class TokenRequest extends GenericData
{
    HttpRequestInitializer requestInitializer;
    HttpExecuteInterceptor clientAuthentication;
    private final HttpTransport transport;
    private final JsonFactory jsonFactory;
    private GenericUrl tokenServerUrl;
    @Key("scope")
    private String scopes;
    @Key("grant_type")
    private String grantType;
    
    public TokenRequest(final HttpTransport httpTransport, final JsonFactory jsonFactory, final GenericUrl tokenServerUrl, final String grantType) {
        super();
        this.transport = Preconditions.<HttpTransport>checkNotNull(httpTransport);
        this.jsonFactory = Preconditions.<JsonFactory>checkNotNull(jsonFactory);
        this.setTokenServerUrl(tokenServerUrl);
        this.setGrantType(grantType);
    }
    
    public final HttpTransport getTransport() {
        return this.transport;
    }
    
    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }
    
    public final HttpRequestInitializer getRequestInitializer() {
        return this.requestInitializer;
    }
    
    public TokenRequest setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        this.requestInitializer = requestInitializer;
        return this;
    }
    
    public final HttpExecuteInterceptor getClientAuthentication() {
        return this.clientAuthentication;
    }
    
    public TokenRequest setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        this.clientAuthentication = clientAuthentication;
        return this;
    }
    
    public final GenericUrl getTokenServerUrl() {
        return this.tokenServerUrl;
    }
    
    public TokenRequest setTokenServerUrl(final GenericUrl tokenServerUrl) {
        this.tokenServerUrl = tokenServerUrl;
        Preconditions.checkArgument(tokenServerUrl.getFragment() == null);
        return this;
    }
    
    public final String getScopes() {
        return this.scopes;
    }
    
    public TokenRequest setScopes(final Collection<String> collection) {
        this.scopes = ((collection == null) ? null : Joiner.on(' ').join(collection));
        return this;
    }
    
    public final String getGrantType() {
        return this.grantType;
    }
    
    public TokenRequest setGrantType(final String s) {
        this.grantType = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final HttpResponse executeUnparsed() throws IOException {
        final HttpRequest buildPostRequest = this.transport.createRequestFactory(new HttpRequestInitializer() {
            final /* synthetic */ TokenRequest this$0;
            
            TokenRequest$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void initialize(final HttpRequest httpRequest) throws IOException {
                if (this.this$0.requestInitializer != null) {
                    this.this$0.requestInitializer.initialize(httpRequest);
                }
                httpRequest.setInterceptor(new HttpExecuteInterceptor() {
                    final /* synthetic */ HttpExecuteInterceptor val$interceptor = httpRequest.getInterceptor();
                    final /* synthetic */ TokenRequest$1 this$1;
                    
                    TokenRequest$1$1() {
                        this.this$1 = this$1;
                        super();
                    }
                    
                    @Override
                    public void intercept(final HttpRequest httpRequest) throws IOException {
                        if (this.val$interceptor != null) {
                            this.val$interceptor.intercept(httpRequest);
                        }
                        if (this.this$1.this$0.clientAuthentication != null) {
                            this.this$1.this$0.clientAuthentication.intercept(httpRequest);
                        }
                    }
                });
            }
        }).buildPostRequest(this.tokenServerUrl, new UrlEncodedContent(this));
        buildPostRequest.setParser(new JsonObjectParser(this.jsonFactory));
        buildPostRequest.setThrowExceptionOnExecuteError(false);
        final HttpResponse execute = buildPostRequest.execute();
        if (execute.isSuccessStatusCode()) {
            return execute;
        }
        throw TokenResponseException.from(this.jsonFactory, execute);
    }
    
    public TokenResponse execute() throws IOException {
        return this.executeUnparsed().<TokenResponse>parseAs(TokenResponse.class);
    }
    
    @Override
    public TokenRequest set(final String s, final Object o) {
        return (TokenRequest)super.set(s, o);
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
}
