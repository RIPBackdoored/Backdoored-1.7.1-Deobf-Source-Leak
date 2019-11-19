package com.google.api.client.http.javanet;

import com.google.api.client.util.Beta;
import javax.net.ssl.SSLContext;
import com.google.api.client.util.SslUtils;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import com.google.api.client.util.SecurityUtils;
import java.io.InputStream;
import java.net.Proxy;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public static final class Builder
{
    private SSLSocketFactory sslSocketFactory;
    private HostnameVerifier hostnameVerifier;
    private Proxy proxy;
    private ConnectionFactory connectionFactory;
    
    public Builder() {
        super();
    }
    
    public Builder setProxy(final Proxy proxy) {
        this.proxy = proxy;
        return this;
    }
    
    public Builder setConnectionFactory(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
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
        return this.setSslSocketFactory(tlsSslContext.getSocketFactory());
    }
    
    @Beta
    public Builder doNotValidateCertificate() throws GeneralSecurityException {
        this.hostnameVerifier = SslUtils.trustAllHostnameVerifier();
        this.sslSocketFactory = SslUtils.trustAllSSLContext().getSocketFactory();
        return this;
    }
    
    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }
    
    public Builder setSslSocketFactory(final SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
        return this;
    }
    
    public HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }
    
    public Builder setHostnameVerifier(final HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }
    
    public NetHttpTransport build() {
        if (System.getProperty("com.google.api.client.should_use_proxy") != null) {
            this.setProxy(NetHttpTransport.access$000());
        }
        return (this.proxy == null) ? new NetHttpTransport(this.connectionFactory, this.sslSocketFactory, this.hostnameVerifier) : new NetHttpTransport(this.proxy, this.sslSocketFactory, this.hostnameVerifier);
    }
}
