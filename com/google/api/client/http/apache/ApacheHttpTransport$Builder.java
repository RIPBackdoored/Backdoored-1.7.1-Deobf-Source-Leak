package com.google.api.client.http.apache;

import org.apache.http.client.HttpClient;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Beta;
import javax.net.ssl.SSLContext;
import com.google.api.client.util.SslUtils;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import com.google.api.client.util.SecurityUtils;
import java.io.InputStream;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.HttpHost;
import java.net.ProxySelector;
import org.apache.http.params.HttpParams;
import org.apache.http.conn.ssl.SSLSocketFactory;

public static final class Builder
{
    private SSLSocketFactory socketFactory;
    private HttpParams params;
    private ProxySelector proxySelector;
    
    public Builder() {
        super();
        this.socketFactory = SSLSocketFactory.getSocketFactory();
        this.params = ApacheHttpTransport.newDefaultHttpParams();
        this.proxySelector = ProxySelector.getDefault();
    }
    
    public Builder setProxy(final HttpHost httpHost) {
        ConnRouteParams.setDefaultProxy(this.params, httpHost);
        if (httpHost != null) {
            this.proxySelector = null;
        }
        return this;
    }
    
    public Builder setProxySelector(final ProxySelector proxySelector) {
        this.proxySelector = proxySelector;
        if (proxySelector != null) {
            ConnRouteParams.setDefaultProxy(this.params, (HttpHost)null);
        }
        return this;
    }
    
    public Builder trustCertificatesFromJavaKeyStore(final InputStream inputStream, final String s) throws GeneralSecurityException, IOException {
        final KeyStore javaKeyStore = SecurityUtils.getJavaKeyStore();
        SecurityUtils.loadKeyStore(javaKeyStore, inputStream, s);
        return this.trustCertificates(javaKeyStore);
    }
    
    public Builder trustCertificatesFromStream(final InputStream inputStream) throws GeneralSecurityException, IOException {
        final KeyStore javaKeyStore = SecurityUtils.getJavaKeyStore();
        javaKeyStore.load(null, null);
        SecurityUtils.loadKeyStoreFromCertificates(javaKeyStore, SecurityUtils.getX509CertificateFactory(), inputStream);
        return this.trustCertificates(javaKeyStore);
    }
    
    public Builder trustCertificates(final KeyStore keyStore) throws GeneralSecurityException {
        final SSLContext tlsSslContext = SslUtils.getTlsSslContext();
        SslUtils.initSslContext(tlsSslContext, keyStore, SslUtils.getPkixTrustManagerFactory());
        return this.setSocketFactory(new SSLSocketFactoryExtension(tlsSslContext));
    }
    
    @Beta
    public Builder doNotValidateCertificate() throws GeneralSecurityException {
        (this.socketFactory = new SSLSocketFactoryExtension(SslUtils.trustAllSSLContext())).setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return this;
    }
    
    public Builder setSocketFactory(final SSLSocketFactory sslSocketFactory) {
        this.socketFactory = Preconditions.<SSLSocketFactory>checkNotNull(sslSocketFactory);
        return this;
    }
    
    public SSLSocketFactory getSSLSocketFactory() {
        return this.socketFactory;
    }
    
    public HttpParams getHttpParams() {
        return this.params;
    }
    
    public ApacheHttpTransport build() {
        return new ApacheHttpTransport((HttpClient)ApacheHttpTransport.newDefaultHttpClient(this.socketFactory, this.params, this.proxySelector));
    }
}
