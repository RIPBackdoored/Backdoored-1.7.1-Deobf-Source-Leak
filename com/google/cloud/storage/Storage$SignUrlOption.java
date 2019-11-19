package com.google.cloud.storage;

import com.google.auth.ServiceAccountSigner;
import java.util.Map;
import java.io.Serializable;

public static class SignUrlOption implements Serializable
{
    private static final long serialVersionUID = 7850569877451099267L;
    private final Option option;
    private final Object value;
    
    private SignUrlOption(final Option option, final Object value) {
        super();
        this.option = option;
        this.value = value;
    }
    
    Option getOption() {
        return this.option;
    }
    
    Object getValue() {
        return this.value;
    }
    
    public static SignUrlOption httpMethod(final HttpMethod httpMethod) {
        return new SignUrlOption(Option.HTTP_METHOD, httpMethod);
    }
    
    public static SignUrlOption withContentType() {
        return new SignUrlOption(Option.CONTENT_TYPE, true);
    }
    
    public static SignUrlOption withMd5() {
        return new SignUrlOption(Option.MD5, true);
    }
    
    public static SignUrlOption withExtHeaders(final Map<String, String> map) {
        return new SignUrlOption(Option.EXT_HEADERS, map);
    }
    
    public static SignUrlOption withV2Signature() {
        return new SignUrlOption(Option.SIGNATURE_VERSION, SignatureVersion.V2);
    }
    
    public static SignUrlOption withV4Signature() {
        return new SignUrlOption(Option.SIGNATURE_VERSION, SignatureVersion.V4);
    }
    
    public static SignUrlOption signWith(final ServiceAccountSigner serviceAccountSigner) {
        return new SignUrlOption(Option.SERVICE_ACCOUNT_CRED, serviceAccountSigner);
    }
    
    public static SignUrlOption withHostName(final String s) {
        return new SignUrlOption(Option.HOST_NAME, s);
    }
}
