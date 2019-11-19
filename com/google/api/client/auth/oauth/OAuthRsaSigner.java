package com.google.api.client.auth.oauth;

import java.security.GeneralSecurityException;
import com.google.api.client.util.Base64;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.SecurityUtils;
import java.security.PrivateKey;
import com.google.api.client.util.Beta;

@Beta
public final class OAuthRsaSigner implements OAuthSigner
{
    public PrivateKey privateKey;
    
    public OAuthRsaSigner() {
        super();
    }
    
    @Override
    public String getSignatureMethod() {
        return "RSA-SHA1";
    }
    
    @Override
    public String computeSignature(final String s) throws GeneralSecurityException {
        return Base64.encodeBase64String(SecurityUtils.sign(SecurityUtils.getSha1WithRsaSignatureAlgorithm(), this.privateKey, StringUtils.getBytesUtf8(s)));
    }
}
