package com.google.api.client.auth.oauth;

import java.security.GeneralSecurityException;
import com.google.api.client.util.Base64;
import java.security.Key;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Beta;

@Beta
public final class OAuthHmacSigner implements OAuthSigner
{
    public String clientSharedSecret;
    public String tokenSharedSecret;
    
    public OAuthHmacSigner() {
        super();
    }
    
    @Override
    public String getSignatureMethod() {
        return "HMAC-SHA1";
    }
    
    @Override
    public String computeSignature(final String s) throws GeneralSecurityException {
        final StringBuilder sb = new StringBuilder();
        final String clientSharedSecret = this.clientSharedSecret;
        if (clientSharedSecret != null) {
            sb.append(OAuthParameters.escape(clientSharedSecret));
        }
        sb.append('&');
        final String tokenSharedSecret = this.tokenSharedSecret;
        if (tokenSharedSecret != null) {
            sb.append(OAuthParameters.escape(tokenSharedSecret));
        }
        final SecretKeySpec secretKeySpec = new SecretKeySpec(StringUtils.getBytesUtf8(sb.toString()), "HmacSHA1");
        final Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(secretKeySpec);
        return Base64.encodeBase64String(instance.doFinal(StringUtils.getBytesUtf8(s)));
    }
}
