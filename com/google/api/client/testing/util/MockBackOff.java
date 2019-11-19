package com.google.api.client.testing.util;

import com.google.api.client.util.Preconditions;
import java.io.IOException;
import com.google.api.client.util.Beta;
import com.google.api.client.util.BackOff;

@Beta
public class MockBackOff implements BackOff
{
    private long backOffMillis;
    private int maxTries;
    private int numTries;
    
    public MockBackOff() {
        super();
        this.maxTries = 10;
    }
    
    @Override
    public void reset() throws IOException {
        this.numTries = 0;
    }
    
    @Override
    public long nextBackOffMillis() throws IOException {
        if (this.numTries >= this.maxTries || this.backOffMillis == -1L) {
            return -1L;
        }
        ++this.numTries;
        return this.backOffMillis;
    }
    
    public MockBackOff setBackOffMillis(final long backOffMillis) {
        Preconditions.checkArgument(backOffMillis == -1L || backOffMillis >= 0L);
        this.backOffMillis = backOffMillis;
        return this;
    }
    
    public MockBackOff setMaxTries(final int maxTries) {
        Preconditions.checkArgument(maxTries >= 0);
        this.maxTries = maxTries;
        return this;
    }
    
    public final int getMaxTries() {
        return this.numTries;
    }
    
    public final int getNumberOfTries() {
        return this.numTries;
    }
}
