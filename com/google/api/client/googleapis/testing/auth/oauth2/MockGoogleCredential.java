package com.google.api.client.googleapis.testing.auth.oauth2;

import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Clock;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

@Beta
public class MockGoogleCredential extends GoogleCredential
{
    public static final String ACCESS_TOKEN = "access_xyz";
    public static final String REFRESH_TOKEN = "refresh123";
    private static final String EXPIRES_IN_SECONDS = "3600";
    private static final String TOKEN_TYPE = "Bearer";
    private static final String TOKEN_RESPONSE = "{\"access_token\": \"%s\", \"expires_in\":  %s, \"refresh_token\": \"%s\", \"token_type\": \"%s\"}";
    private static final String DEFAULT_TOKEN_RESPONSE_JSON;
    
    public MockGoogleCredential(final Builder builder) {
        super(builder);
    }
    
    public static MockHttpTransport newMockHttpTransportWithSampleTokenResponse() {
        return new MockHttpTransport.Builder().setLowLevelHttpRequest(new MockLowLevelHttpRequest().setResponse(new MockLowLevelHttpResponse().setContentType("application/json; charset=UTF-8").setContent(MockGoogleCredential.DEFAULT_TOKEN_RESPONSE_JSON))).build();
    }
    
    static {
        DEFAULT_TOKEN_RESPONSE_JSON = String.format("{\"access_token\": \"%s\", \"expires_in\":  %s, \"refresh_token\": \"%s\", \"token_type\": \"%s\"}", "access_xyz", "3600", "refresh123", "Bearer");
    }
}
