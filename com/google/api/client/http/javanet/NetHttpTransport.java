package com.google.api.client.http.javanet;

import com.google.api.client.util.Beta;
import javax.net.ssl.SSLContext;
import com.google.api.client.util.SslUtils;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import com.google.api.client.util.SecurityUtils;
import java.io.InputStream;
import com.google.api.client.http.LowLevelHttpRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import com.google.api.client.util.Preconditions;
import java.util.Arrays;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import com.google.api.client.http.HttpTransport;

public final class NetHttpTransport extends HttpTransport
{
    private static final String[] SUPPORTED_METHODS;
    private static final String SHOULD_USE_PROXY_FLAG = "com.google.api.client.should_use_proxy";
    private final ConnectionFactory connectionFactory;
    private final SSLSocketFactory sslSocketFactory;
    private final HostnameVerifier hostnameVerifier;
    
    private static Proxy defaultProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("https.proxyHost"), Integer.parseInt(System.getProperty("https.proxyPort"))));
    }
    
    public NetHttpTransport() {
        this((ConnectionFactory)null, null, null);
    }
    
    NetHttpTransport(final Proxy proxy, final SSLSocketFactory sslSocketFactory, final HostnameVerifier hostnameVerifier) {
        this(new DefaultConnectionFactory(proxy), sslSocketFactory, hostnameVerifier);
    }
    
    NetHttpTransport(final ConnectionFactory connectionFactory, final SSLSocketFactory sslSocketFactory, final HostnameVerifier hostnameVerifier) {
        super();
        this.connectionFactory = this.getConnectionFactory(connectionFactory);
        this.sslSocketFactory = sslSocketFactory;
        this.hostnameVerifier = hostnameVerifier;
    }
    
    private ConnectionFactory getConnectionFactory(final ConnectionFactory connectionFactory) {
        if (connectionFactory != null) {
            return connectionFactory;
        }
        if (System.getProperty("com.google.api.client.should_use_proxy") != null) {
            return new DefaultConnectionFactory(defaultProxy());
        }
        return new DefaultConnectionFactory();
    }
    
    @Override
    public boolean supportsMethod(final String s) {
        return Arrays.binarySearch(NetHttpTransport.SUPPORTED_METHODS, s) >= 0;
    }
    
    @Override
    protected NetHttpRequest buildRequest(final String requestMethod, final String s) throws IOException {
        Preconditions.checkArgument(this.supportsMethod(requestMethod), "HTTP method %s not supported", requestMethod);
        final HttpURLConnection openConnection = this.connectionFactory.openConnection(new URL(s));
        openConnection.setRequestMethod(requestMethod);
        if (openConnection instanceof HttpsURLConnection) {
            final HttpsURLConnection httpsURLConnection = (HttpsURLConnection)openConnection;
            if (this.hostnameVerifier != null) {
                httpsURLConnection.setHostnameVerifier(this.hostnameVerifier);
            }
            if (this.sslSocketFactory != null) {
                httpsURLConnection.setSSLSocketFactory(this.sslSocketFactory);
            }
        }
        return new NetHttpRequest(openConnection);
    }
    
    @Override
    protected /* bridge */ LowLevelHttpRequest buildRequest(final String s, final String s2) throws IOException {
        return this.buildRequest(s, s2);
    }
    
    static /* synthetic */ Proxy access$000() {
        return defaultProxy();
    }
    
    static {
        Arrays.sort(SUPPORTED_METHODS = new String[] { "DELETE", "GET", "HEAD", "OPTIONS", "POST", "PUT", "TRACE" });
    }
}
