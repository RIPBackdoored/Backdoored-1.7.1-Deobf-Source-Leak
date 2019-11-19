package com.google.api.client.http;

import java.io.IOException;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import com.google.api.client.util.BackOff;
import com.google.api.client.util.Beta;

@Beta
public class HttpBackOffUnsuccessfulResponseHandler implements HttpUnsuccessfulResponseHandler
{
    private final BackOff backOff;
    private BackOffRequired backOffRequired;
    private Sleeper sleeper;
    
    public HttpBackOffUnsuccessfulResponseHandler(final BackOff backOff) {
        super();
        this.backOffRequired = BackOffRequired.ON_SERVER_ERROR;
        this.sleeper = Sleeper.DEFAULT;
        this.backOff = Preconditions.<BackOff>checkNotNull(backOff);
    }
    
    public final BackOff getBackOff() {
        return this.backOff;
    }
    
    public final BackOffRequired getBackOffRequired() {
        return this.backOffRequired;
    }
    
    public HttpBackOffUnsuccessfulResponseHandler setBackOffRequired(final BackOffRequired backOffRequired) {
        this.backOffRequired = Preconditions.<BackOffRequired>checkNotNull(backOffRequired);
        return this;
    }
    
    public final Sleeper getSleeper() {
        return this.sleeper;
    }
    
    public HttpBackOffUnsuccessfulResponseHandler setSleeper(final Sleeper sleeper) {
        this.sleeper = Preconditions.<Sleeper>checkNotNull(sleeper);
        return this;
    }
    
    @Override
    public final boolean handleResponse(final HttpRequest httpRequest, final HttpResponse httpResponse, final boolean b) throws IOException {
        return false;
    }
}
