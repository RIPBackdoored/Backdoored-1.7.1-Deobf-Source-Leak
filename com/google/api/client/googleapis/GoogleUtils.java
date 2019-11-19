package com.google.api.client.googleapis;

import java.security.GeneralSecurityException;
import java.io.IOException;
import com.google.api.client.util.SecurityUtils;
import java.security.KeyStore;

public final class GoogleUtils
{
    public static final Integer MAJOR_VERSION;
    public static final Integer MINOR_VERSION;
    public static final Integer BUGFIX_VERSION;
    public static final String VERSION;
    static KeyStore certTrustStore;
    
    public static synchronized KeyStore getCertificateTrustStore() throws IOException, GeneralSecurityException {
        if (GoogleUtils.certTrustStore == null) {
            SecurityUtils.loadKeyStore(GoogleUtils.certTrustStore = SecurityUtils.getJavaKeyStore(), GoogleUtils.class.getResourceAsStream("google.jks"), "notasecret");
        }
        return GoogleUtils.certTrustStore;
    }
    
    private GoogleUtils() {
        super();
    }
    
    static {
        MAJOR_VERSION = 1;
        MINOR_VERSION = 23;
        BUGFIX_VERSION = 0;
        final String value = String.valueOf(String.valueOf(GoogleUtils.MAJOR_VERSION));
        final String value2 = String.valueOf(String.valueOf(GoogleUtils.MINOR_VERSION));
        final String value3 = String.valueOf(String.valueOf(GoogleUtils.BUGFIX_VERSION));
        VERSION = new StringBuilder(2 + value.length() + value2.length() + value3.length()).append(value).append(".").append(value2).append(".").append(value3).toString().toString();
    }
}
