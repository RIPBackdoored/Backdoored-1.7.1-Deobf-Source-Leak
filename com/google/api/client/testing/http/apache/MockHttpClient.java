package com.google.api.client.testing.http.apache;

import com.google.api.client.util.Preconditions;
import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.apache.http.HttpRequest;
import org.apache.http.HttpHost;
import org.apache.http.client.RequestDirector;
import org.apache.http.params.HttpParams;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.protocol.HttpRequestExecutor;
import com.google.api.client.util.Beta;
import org.apache.http.impl.client.DefaultHttpClient;

@Beta
public class MockHttpClient extends DefaultHttpClient
{
    int responseCode;
    
    public MockHttpClient() {
        super();
    }
    
    protected RequestDirector createClientRequestDirector(final HttpRequestExecutor httpRequestExecutor, final ClientConnectionManager clientConnectionManager, final ConnectionReuseStrategy connectionReuseStrategy, final ConnectionKeepAliveStrategy connectionKeepAliveStrategy, final HttpRoutePlanner httpRoutePlanner, final HttpProcessor httpProcessor, final HttpRequestRetryHandler httpRequestRetryHandler, final RedirectHandler redirectHandler, final AuthenticationHandler authenticationHandler, final AuthenticationHandler authenticationHandler2, final UserTokenHandler userTokenHandler, final HttpParams httpParams) {
        return (RequestDirector)new RequestDirector() {
            final /* synthetic */ MockHttpClient this$0;
            
            MockHttpClient$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Beta
            public HttpResponse execute(final HttpHost httpHost, final HttpRequest httpRequest, final HttpContext httpContext) throws HttpException, IOException {
                return (HttpResponse)new BasicHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_1, this.this$0.responseCode, (String)null);
            }
        };
    }
    
    public final int getResponseCode() {
        return this.responseCode;
    }
    
    public MockHttpClient setResponseCode(final int responseCode) {
        Preconditions.checkArgument(responseCode >= 0);
        this.responseCode = responseCode;
        return this;
    }
}
