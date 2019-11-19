package com.google.api.client.googleapis.apache;

import java.io.IOException;
import java.security.GeneralSecurityException;
import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.http.apache.ApacheHttpTransport;

public final class GoogleApacheHttpTransport
{
    public static ApacheHttpTransport newTrustedTransport() throws GeneralSecurityException, IOException {
        return new ApacheHttpTransport.Builder().trustCertificates(GoogleUtils.getCertificateTrustStore()).build();
    }
    
    private GoogleApacheHttpTransport() {
        super();
    }
}
