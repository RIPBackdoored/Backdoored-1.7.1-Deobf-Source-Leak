package com.google.api.client.http;

import java.io.IOException;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import com.google.api.client.util.BackOff;
import com.google.api.client.util.Beta;

@Beta
public class HttpBackOffIOExceptionHandler implements HttpIOExceptionHandler
{
    private final BackOff backOff;
    private Sleeper sleeper;
    
    public HttpBackOffIOExceptionHandler(final BackOff backOff) {
        super();
        this.sleeper = Sleeper.DEFAULT;
        this.backOff = Preconditions.<BackOff>checkNotNull(backOff);
    }
    
    public final BackOff getBackOff() {
        return this.backOff;
    }
    
    public final Sleeper getSleeper() {
        return this.sleeper;
    }
    
    public HttpBackOffIOExceptionHandler setSleeper(final Sleeper sleeper) {
        this.sleeper = Preconditions.<Sleeper>checkNotNull(sleeper);
        return this;
    }
    
    @Override
    public boolean handleIOException(final HttpRequest httpRequest, final boolean b) throws IOException {
        return false;
    }
}
