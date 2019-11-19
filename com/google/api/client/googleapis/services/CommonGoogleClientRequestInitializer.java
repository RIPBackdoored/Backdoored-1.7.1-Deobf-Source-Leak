package com.google.api.client.googleapis.services;

import java.io.IOException;

public class CommonGoogleClientRequestInitializer implements GoogleClientRequestInitializer
{
    private final String key;
    private final String userIp;
    
    public CommonGoogleClientRequestInitializer() {
        this(null);
    }
    
    public CommonGoogleClientRequestInitializer(final String s) {
        this(s, null);
    }
    
    public CommonGoogleClientRequestInitializer(final String key, final String userIp) {
        super();
        this.key = key;
        this.userIp = userIp;
    }
    
    public void initialize(final AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
        if (this.key != null) {
            abstractGoogleClientRequest.put("key", this.key);
        }
        if (this.userIp != null) {
            abstractGoogleClientRequest.put("userIp", this.userIp);
        }
    }
    
    public final String getKey() {
        return this.key;
    }
    
    public final String getUserIp() {
        return this.userIp;
    }
}
