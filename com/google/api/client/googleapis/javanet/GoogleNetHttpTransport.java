package com.google.api.client.googleapis.javanet;

import java.io.IOException;
import java.security.GeneralSecurityException;
import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.http.javanet.NetHttpTransport;

public class GoogleNetHttpTransport
{
    public static NetHttpTransport newTrustedTransport() throws GeneralSecurityException, IOException {
        return new NetHttpTransport.Builder().trustCertificates(GoogleUtils.getCertificateTrustStore()).build();
    }
    
    private GoogleNetHttpTransport() {
        super();
    }
}
