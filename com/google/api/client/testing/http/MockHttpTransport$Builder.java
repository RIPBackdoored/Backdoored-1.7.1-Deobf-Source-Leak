package com.google.api.client.testing.http;

import com.google.api.client.util.Preconditions;
import java.util.Set;
import com.google.api.client.util.Beta;

@Beta
public static class Builder
{
    Set<String> supportedMethods;
    MockLowLevelHttpRequest lowLevelHttpRequest;
    MockLowLevelHttpResponse lowLevelHttpResponse;
    
    public Builder() {
        super();
    }
    
    public MockHttpTransport build() {
        return new MockHttpTransport(this);
    }
    
    public final Set<String> getSupportedMethods() {
        return this.supportedMethods;
    }
    
    public final Builder setSupportedMethods(final Set<String> supportedMethods) {
        this.supportedMethods = supportedMethods;
        return this;
    }
    
    public final Builder setLowLevelHttpRequest(final MockLowLevelHttpRequest lowLevelHttpRequest) {
        Preconditions.checkState(this.lowLevelHttpResponse == null, (Object)"Cannnot set a low level HTTP request when a low level HTTP response has been set.");
        this.lowLevelHttpRequest = lowLevelHttpRequest;
        return this;
    }
    
    public final MockLowLevelHttpRequest getLowLevelHttpRequest() {
        return this.lowLevelHttpRequest;
    }
    
    public final Builder setLowLevelHttpResponse(final MockLowLevelHttpResponse lowLevelHttpResponse) {
        Preconditions.checkState(this.lowLevelHttpRequest == null, (Object)"Cannot set a low level HTTP response when a low level HTTP request has been set.");
        this.lowLevelHttpResponse = lowLevelHttpResponse;
        return this;
    }
    
    MockLowLevelHttpResponse getLowLevelHttpResponse() {
        return this.lowLevelHttpResponse;
    }
}
