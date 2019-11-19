package com.google.api.client.auth.oauth;

import java.security.GeneralSecurityException;
import com.google.api.client.util.Beta;

@Beta
public interface OAuthSigner
{
    String getSignatureMethod();
    
    String computeSignature(final String p0) throws GeneralSecurityException;
}
