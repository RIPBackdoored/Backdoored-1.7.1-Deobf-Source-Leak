package com.google.api.client.extensions.java6.auth.oauth2;

import java.io.IOException;

public interface VerificationCodeReceiver
{
    String getRedirectUri() throws IOException;
    
    String waitForCode() throws IOException;
    
    void stop() throws IOException;
}
