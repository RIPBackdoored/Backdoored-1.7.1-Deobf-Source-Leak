package com.google.api.client.http.apache;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Beta;
import javax.net.ssl.SSLContext;
import com.google.api.client.util.SslUtils;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import com.google.api.client.util.SecurityUtils;
import java.io.InputStream;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.HttpHost;
import java.io.IOException;
import com.google.api.client.http.LowLevelHttpRequest;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.impl.conn.ProxySelectorRoutePlanner;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.BasicHttpParams;
import java.net.ProxySelector;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.ProtocolVersion;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import com.google.api.client.http.HttpTransport;

public final class ApacheHttpTransport extends HttpTransport
{
    private final HttpClient httpClient;
    
    public ApacheHttpTransport() {
        this((HttpClient)newDefaultHttpClient());
    }
    
    public ApacheHttpTransport(final HttpClient httpClient) {
        super();
        this.httpClient = httpClient;
        HttpParams httpParams = httpClient.getParams();
        if (httpParams == null) {
            httpParams = newDefaultHttpClient().getParams();
        }
        HttpProtocolParams.setVersion(httpParams, (ProtocolVersion)HttpVersion.HTTP_1_1);
        httpParams.setBooleanParameter("http.protocol.handle-redirects", false);
    }
    
    public static DefaultHttpClient newDefaultHttpClient() {
        return newDefaultHttpClient(SSLSocketFactory.getSocketFactory(), newDefaultHttpParams(), ProxySelector.getDefault());
    }
    
    static HttpParams newDefaultHttpParams() {
        final BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled((HttpParams)basicHttpParams, false);
        HttpConnectionParams.setSocketBufferSize((HttpParams)basicHttpParams, 8192);
        ConnManagerParams.setMaxTotalConnections((HttpParams)basicHttpParams, 200);
        ConnManagerParams.setMaxConnectionsPerRoute((HttpParams)basicHttpParams, (ConnPerRoute)new ConnPerRouteBean(20));
        return (HttpParams)basicHttpParams;
    }
    
    static DefaultHttpClient newDefaultHttpClient(final SSLSocketFactory sslSocketFactory, final HttpParams httpParams, final ProxySelector proxySelector) {
        final SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", (SocketFactory)PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", (SocketFactory)sslSocketFactory, 443));
        final DefaultHttpClient defaultHttpClient = new DefaultHttpClient((ClientConnectionManager)new ThreadSafeClientConnManager(httpParams, schemeRegistry), httpParams);
        defaultHttpClient.setHttpRequestRetryHandler((HttpRequestRetryHandler)new DefaultHttpRequestRetryHandler(0, false));
        if (proxySelector != null) {
            defaultHttpClient.setRoutePlanner((HttpRoutePlanner)new ProxySelectorRoutePlanner(schemeRegistry, proxySelector));
        }
        return defaultHttpClient;
    }
    
    @Override
    public boolean supportsMethod(final String s) {
        return true;
    }
    
    @Override
    protected ApacheHttpRequest buildRequest(final String s, final String s2) {
        Object o;
        if (s.equals("DELETE")) {
            o = new HttpDelete(s2);
        }
        else if (s.equals("GET")) {
            o = new HttpGet(s2);
        }
        else if (s.equals("HEAD")) {
            o = new HttpHead(s2);
        }
        else if (s.equals("POST")) {
            o = new HttpPost(s2);
        }
        else if (s.equals("PUT")) {
            o = new HttpPut(s2);
        }
        else if (s.equals("TRACE")) {
            o = new HttpTrace(s2);
        }
        else if (s.equals("OPTIONS")) {
            o = new HttpOptions(s2);
        }
        else {
            o = new HttpExtensionMethod(s, s2);
        }
        return new ApacheHttpRequest(this.httpClient, (HttpRequestBase)o);
    }
    
    @Override
    public void shutdown() {
        this.httpClient.getConnectionManager().shutdown();
    }
    
    public HttpClient getHttpClient() {
        return this.httpClient;
    }
    
    @Override
    protected /* bridge */ LowLevelHttpRequest buildRequest(final String s, final String s2) throws IOException {
        return this.buildRequest(s, s2);
    }
}
