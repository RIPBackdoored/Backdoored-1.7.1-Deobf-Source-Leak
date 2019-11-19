package com.google.api.client.util;

import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;

static final class SslUtils$2 implements HostnameVerifier {
    SslUtils$2() {
        super();
    }
    
    @Override
    public boolean verify(final String s, final SSLSession sslSession) {
        return true;
    }
}