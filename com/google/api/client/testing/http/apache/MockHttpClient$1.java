package com.google.api.client.testing.http.apache;

import com.google.api.client.util.Beta;
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

class MockHttpClient$1 implements RequestDirector {
    final /* synthetic */ MockHttpClient this$0;
    
    MockHttpClient$1(final MockHttpClient this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Beta
    public HttpResponse execute(final HttpHost httpHost, final HttpRequest httpRequest, final HttpContext httpContext) throws HttpException, IOException {
        return (HttpResponse)new BasicHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_1, this.this$0.responseCode, (String)null);
    }
}