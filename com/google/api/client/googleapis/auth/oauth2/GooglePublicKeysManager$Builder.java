package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.util.Preconditions;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Beta;

@Beta
public static class Builder
{
    Clock clock;
    final HttpTransport transport;
    final JsonFactory jsonFactory;
    String publicCertsEncodedUrl;
    
    public Builder(final HttpTransport httpTransport, final JsonFactory jsonFactory) {
        super();
        this.clock = Clock.SYSTEM;
        this.publicCertsEncodedUrl = "https://www.googleapis.com/oauth2/v1/certs";
        this.transport = Preconditions.<HttpTransport>checkNotNull(httpTransport);
        this.jsonFactory = Preconditions.<JsonFactory>checkNotNull(jsonFactory);
    }
    
    public GooglePublicKeysManager build() {
        return new GooglePublicKeysManager(this);
    }
    
    public final HttpTransport getTransport() {
        return this.transport;
    }
    
    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }
    
    public final String getPublicCertsEncodedUrl() {
        return this.publicCertsEncodedUrl;
    }
    
    public Builder setPublicCertsEncodedUrl(final String s) {
        this.publicCertsEncodedUrl = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final Clock getClock() {
        return this.clock;
    }
    
    public Builder setClock(final Clock clock) {
        this.clock = Preconditions.<Clock>checkNotNull(clock);
        return this;
    }
}
