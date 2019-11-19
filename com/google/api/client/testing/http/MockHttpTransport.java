package com.google.api.client.testing.http;

import java.util.Collections;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.LowLevelHttpRequest;
import java.io.IOException;
import java.util.Set;
import com.google.api.client.util.Beta;
import com.google.api.client.http.HttpTransport;

@Beta
public class MockHttpTransport extends HttpTransport
{
    private Set<String> supportedMethods;
    private MockLowLevelHttpRequest lowLevelHttpRequest;
    private MockLowLevelHttpResponse lowLevelHttpResponse;
    
    public MockHttpTransport() {
        super();
    }
    
    protected MockHttpTransport(final Builder builder) {
        super();
        this.supportedMethods = builder.supportedMethods;
        this.lowLevelHttpRequest = builder.lowLevelHttpRequest;
        this.lowLevelHttpResponse = builder.lowLevelHttpResponse;
    }
    
    @Override
    public boolean supportsMethod(final String s) throws IOException {
        return this.supportedMethods == null || this.supportedMethods.contains(s);
    }
    
    public LowLevelHttpRequest buildRequest(final String s, final String s2) throws IOException {
        Preconditions.checkArgument(this.supportsMethod(s), "HTTP method %s not supported", s);
        if (this.lowLevelHttpRequest != null) {
            return this.lowLevelHttpRequest;
        }
        this.lowLevelHttpRequest = new MockLowLevelHttpRequest(s2);
        if (this.lowLevelHttpResponse != null) {
            this.lowLevelHttpRequest.setResponse(this.lowLevelHttpResponse);
        }
        return this.lowLevelHttpRequest;
    }
    
    public final Set<String> getSupportedMethods() {
        return (this.supportedMethods == null) ? null : Collections.<String>unmodifiableSet((Set<? extends String>)this.supportedMethods);
    }
    
    public final MockLowLevelHttpRequest getLowLevelHttpRequest() {
        return this.lowLevelHttpRequest;
    }
    
    @Deprecated
    public static Builder builder() {
        return new Builder();
    }
}
