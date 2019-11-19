package com.google.api.client.testing.json.webtoken;

import java.security.GeneralSecurityException;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import javax.net.ssl.X509TrustManager;
import com.google.api.client.util.Base64;
import java.io.Reader;
import com.google.api.client.util.PemReader;
import java.io.StringReader;
import java.security.cert.CertificateException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import com.google.api.client.util.SecurityUtils;
import java.security.cert.Certificate;
import com.google.api.client.util.Beta;

@Beta
public static class CertData
{
    private String pem;
    
    public CertData(final String pem) {
        super();
        this.pem = pem;
    }
    
    public Certificate getCertfificate() throws IOException, CertificateException {
        return SecurityUtils.getX509CertificateFactory().generateCertificate(new ByteArrayInputStream(this.getDer()));
    }
    
    public byte[] getDer() throws IOException {
        return PemReader.readFirstSectionAndClose(new StringReader(this.pem), "CERTIFICATE").getBase64DecodedBytes();
    }
    
    public String getBase64Der() throws IOException {
        return Base64.encodeBase64String(this.getDer());
    }
    
    public X509TrustManager getTrustManager() throws IOException, GeneralSecurityException {
        final KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
        instance.load(null, null);
        instance.setCertificateEntry("ca", this.getCertfificate());
        final TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance2.init(instance);
        return (X509TrustManager)instance2.getTrustManagers()[0];
    }
}
