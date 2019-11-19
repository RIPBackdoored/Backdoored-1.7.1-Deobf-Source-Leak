package com.google.api.client.extensions.jetty.auth.oauth2;

public static final class Builder
{
    private String host;
    private int port;
    private String successLandingPageUrl;
    private String failureLandingPageUrl;
    private String callbackPath;
    
    public Builder() {
        super();
        this.host = "localhost";
        this.port = -1;
        this.callbackPath = "/Callback";
    }
    
    public LocalServerReceiver build() {
        return new LocalServerReceiver(this.host, this.port, this.callbackPath, this.successLandingPageUrl, this.failureLandingPageUrl);
    }
    
    public String getHost() {
        return this.host;
    }
    
    public Builder setHost(final String host) {
        this.host = host;
        return this;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public Builder setPort(final int port) {
        this.port = port;
        return this;
    }
    
    public String getCallbackPath() {
        return this.callbackPath;
    }
    
    public Builder setCallbackPath(final String callbackPath) {
        this.callbackPath = callbackPath;
        return this;
    }
    
    public Builder setLandingPages(final String successLandingPageUrl, final String failureLandingPageUrl) {
        this.successLandingPageUrl = successLandingPageUrl;
        this.failureLandingPageUrl = failureLandingPageUrl;
        return this;
    }
}
