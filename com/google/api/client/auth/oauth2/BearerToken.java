package com.google.api.client.auth.oauth2;

import com.google.api.client.util.Data;
import com.google.api.client.http.UrlEncodedContent;
import java.util.Map;
import com.google.api.client.util.Preconditions;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import java.util.regex.Pattern;

public class BearerToken
{
    static final String PARAM_NAME = "access_token";
    static final Pattern INVALID_TOKEN_ERROR;
    
    public BearerToken() {
        super();
    }
    
    public static Credential.AccessMethod authorizationHeaderAccessMethod() {
        return new AuthorizationHeaderAccessMethod();
    }
    
    public static Credential.AccessMethod formEncodedBodyAccessMethod() {
        return new FormEncodedBodyAccessMethod();
    }
    
    public static Credential.AccessMethod queryParameterAccessMethod() {
        return new QueryParameterAccessMethod();
    }
    
    static {
        INVALID_TOKEN_ERROR = Pattern.compile("\\s*error\\s*=\\s*\"?invalid_token\"?");
    }
}
