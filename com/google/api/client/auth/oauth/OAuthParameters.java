package com.google.api.client.auth.oauth;

import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.escape.PercentEscaper;
import java.security.SecureRandom;
import com.google.api.client.util.Beta;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpExecuteInterceptor;

@Beta
public final class OAuthParameters implements HttpExecuteInterceptor, HttpRequestInitializer
{
    private static final SecureRandom RANDOM;
    public OAuthSigner signer;
    public String callback;
    public String consumerKey;
    public String nonce;
    public String realm;
    public String signature;
    public String signatureMethod;
    public String timestamp;
    public String token;
    public String verifier;
    public String version;
    private static final PercentEscaper ESCAPER;
    
    public OAuthParameters() {
        super();
    }
    
    public void computeNonce() {
        this.nonce = Long.toHexString(Math.abs(OAuthParameters.RANDOM.nextLong()));
    }
    
    public void computeTimestamp() {
        this.timestamp = Long.toString(System.currentTimeMillis() / 1000L);
    }
    
    public void computeSignature(final String s, final GenericUrl genericUrl) throws GeneralSecurityException {
        final OAuthSigner signer = this.signer;
        final String signatureMethod = signer.getSignatureMethod();
        this.signatureMethod = signatureMethod;
        final String s2 = signatureMethod;
        final TreeMap<String, String> treeMap = new TreeMap<String, String>();
        this.putParameterIfValueNotNull(treeMap, "oauth_callback", this.callback);
        this.putParameterIfValueNotNull(treeMap, "oauth_consumer_key", this.consumerKey);
        this.putParameterIfValueNotNull(treeMap, "oauth_nonce", this.nonce);
        this.putParameterIfValueNotNull(treeMap, "oauth_signature_method", s2);
        this.putParameterIfValueNotNull(treeMap, "oauth_timestamp", this.timestamp);
        this.putParameterIfValueNotNull(treeMap, "oauth_token", this.token);
        this.putParameterIfValueNotNull(treeMap, "oauth_verifier", this.verifier);
        this.putParameterIfValueNotNull(treeMap, "oauth_version", this.version);
        for (final Map.Entry<String, Object> entry : genericUrl.entrySet()) {
            final Collection<Object> value = entry.getValue();
            if (value != null) {
                final String s3 = entry.getKey();
                if (value instanceof Collection) {
                    final Iterator<Object> iterator2 = value.iterator();
                    while (iterator2.hasNext()) {
                        this.putParameter(treeMap, s3, iterator2.next());
                    }
                }
                else {
                    this.putParameter(treeMap, s3, value);
                }
            }
        }
        final StringBuilder sb = new StringBuilder();
        int n = 1;
        for (final Map.Entry<String, String> entry2 : treeMap.entrySet()) {
            if (n != 0) {
                n = 0;
            }
            else {
                sb.append('&');
            }
            sb.append(entry2.getKey());
            final String s4 = entry2.getValue();
            if (s4 != null) {
                sb.append('=').append(s4);
            }
        }
        final String string = sb.toString();
        final GenericUrl genericUrl2 = new GenericUrl();
        final String scheme = genericUrl.getScheme();
        genericUrl2.setScheme(scheme);
        genericUrl2.setHost(genericUrl.getHost());
        genericUrl2.setPathParts(genericUrl.getPathParts());
        int port = genericUrl.getPort();
        if (("http".equals(scheme) && port == 80) || ("https".equals(scheme) && port == 443)) {
            port = -1;
        }
        genericUrl2.setPort(port);
        final String build = genericUrl2.build();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(escape(s)).append('&');
        sb2.append(escape(build)).append('&');
        sb2.append(escape(string));
        this.signature = signer.computeSignature(sb2.toString());
    }
    
    public String getAuthorizationHeader() {
        final StringBuilder sb = new StringBuilder("OAuth");
        this.appendParameter(sb, "realm", this.realm);
        this.appendParameter(sb, "oauth_callback", this.callback);
        this.appendParameter(sb, "oauth_consumer_key", this.consumerKey);
        this.appendParameter(sb, "oauth_nonce", this.nonce);
        this.appendParameter(sb, "oauth_signature", this.signature);
        this.appendParameter(sb, "oauth_signature_method", this.signatureMethod);
        this.appendParameter(sb, "oauth_timestamp", this.timestamp);
        this.appendParameter(sb, "oauth_token", this.token);
        this.appendParameter(sb, "oauth_verifier", this.verifier);
        this.appendParameter(sb, "oauth_version", this.version);
        return sb.substring(0, sb.length() - 1);
    }
    
    private void appendParameter(final StringBuilder sb, final String s, final String s2) {
        if (s2 != null) {
            sb.append(' ').append(escape(s)).append("=\"").append(escape(s2)).append("\",");
        }
    }
    
    private void putParameterIfValueNotNull(final TreeMap<String, String> treeMap, final String s, final String s2) {
        if (s2 != null) {
            this.putParameter(treeMap, s, s2);
        }
    }
    
    private void putParameter(final TreeMap<String, String> treeMap, final String s, final Object o) {
        treeMap.put(escape(s), (o == null) ? null : escape(o.toString()));
    }
    
    public static String escape(final String s) {
        return OAuthParameters.ESCAPER.escape(s);
    }
    
    @Override
    public void initialize(final HttpRequest httpRequest) throws IOException {
        httpRequest.setInterceptor(this);
    }
    
    @Override
    public void intercept(final HttpRequest httpRequest) throws IOException {
        this.computeNonce();
        this.computeTimestamp();
        try {
            this.computeSignature(httpRequest.getRequestMethod(), httpRequest.getUrl());
        }
        catch (GeneralSecurityException ex2) {
            final IOException ex = new IOException();
            ex.initCause(ex2);
            throw ex;
        }
        httpRequest.getHeaders().setAuthorization(this.getAuthorizationHeader());
    }
    
    static {
        RANDOM = new SecureRandom();
        ESCAPER = new PercentEscaper("-_.~", false);
    }
}
