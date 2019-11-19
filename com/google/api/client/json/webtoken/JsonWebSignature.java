package com.google.api.client.json.webtoken;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import com.google.api.client.util.GenericData;
import com.google.api.client.json.GenericJson;
import java.util.ArrayList;
import com.google.api.client.util.Key;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Base64;
import java.security.PrivateKey;
import java.io.IOException;
import com.google.api.client.json.JsonFactory;
import javax.net.ssl.TrustManager;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyStore;
import javax.net.ssl.TrustManagerFactory;
import com.google.api.client.util.Beta;
import java.util.List;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import java.security.GeneralSecurityException;
import com.google.api.client.util.SecurityUtils;
import java.security.PublicKey;
import com.google.api.client.util.Preconditions;

public class JsonWebSignature extends JsonWebToken
{
    private final byte[] signatureBytes;
    private final byte[] signedContentBytes;
    
    public JsonWebSignature(final Header header, final Payload payload, final byte[] array, final byte[] array2) {
        super(header, payload);
        this.signatureBytes = Preconditions.<byte[]>checkNotNull(array);
        this.signedContentBytes = Preconditions.<byte[]>checkNotNull(array2);
    }
    
    @Override
    public Header getHeader() {
        return (Header)super.getHeader();
    }
    
    public final boolean verifySignature(final PublicKey publicKey) throws GeneralSecurityException {
        return "RS256".equals(this.getHeader().getAlgorithm()) && SecurityUtils.verify(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), publicKey, this.signatureBytes, this.signedContentBytes);
    }
    
    @Beta
    public final X509Certificate verifySignature(final X509TrustManager x509TrustManager) throws GeneralSecurityException {
        final List<String> x509Certificates = this.getHeader().getX509Certificates();
        if (x509Certificates == null || x509Certificates.isEmpty()) {
            return null;
        }
        if ("RS256".equals(this.getHeader().getAlgorithm())) {
            return SecurityUtils.verify(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), x509TrustManager, x509Certificates, this.signatureBytes, this.signedContentBytes);
        }
        return null;
    }
    
    @Beta
    public final X509Certificate verifySignature() throws GeneralSecurityException {
        final X509TrustManager defaultX509TrustManager = getDefaultX509TrustManager();
        if (defaultX509TrustManager == null) {
            return null;
        }
        return this.verifySignature(defaultX509TrustManager);
    }
    
    private static X509TrustManager getDefaultX509TrustManager() {
        try {
            final TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore)null);
            for (final TrustManager trustManager : instance.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    return (X509TrustManager)trustManager;
                }
            }
            return null;
        }
        catch (NoSuchAlgorithmException ex) {
            return null;
        }
        catch (KeyStoreException ex2) {
            return null;
        }
    }
    
    public final byte[] getSignatureBytes() {
        return this.signatureBytes;
    }
    
    public final byte[] getSignedContentBytes() {
        return this.signedContentBytes;
    }
    
    public static JsonWebSignature parse(final JsonFactory jsonFactory, final String s) throws IOException {
        return parser(jsonFactory).parse(s);
    }
    
    public static Parser parser(final JsonFactory jsonFactory) {
        return new Parser(jsonFactory);
    }
    
    public static String signUsingRsaSha256(final PrivateKey privateKey, final JsonFactory jsonFactory, final Header header, final Payload payload) throws GeneralSecurityException, IOException {
        final String string = Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(header)) + "." + Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(payload));
        return string + "." + Base64.encodeBase64URLSafeString(SecurityUtils.sign(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), privateKey, StringUtils.getBytesUtf8(string)));
    }
    
    @Override
    public /* bridge */ JsonWebToken.Header getHeader() {
        return this.getHeader();
    }
}
