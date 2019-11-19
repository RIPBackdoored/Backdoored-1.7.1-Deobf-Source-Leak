package com.google.api.client.http;

import com.google.api.client.util.NanoClock;
import java.io.IOException;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.Beta;

@Deprecated
@Beta
public class ExponentialBackOffPolicy implements BackOffPolicy
{
    public static final int DEFAULT_INITIAL_INTERVAL_MILLIS = 500;
    public static final double DEFAULT_RANDOMIZATION_FACTOR = 0.5;
    public static final double DEFAULT_MULTIPLIER = 1.5;
    public static final int DEFAULT_MAX_INTERVAL_MILLIS = 60000;
    public static final int DEFAULT_MAX_ELAPSED_TIME_MILLIS = 900000;
    private final ExponentialBackOff exponentialBackOff;
    
    public ExponentialBackOffPolicy() {
        this(new Builder());
    }
    
    protected ExponentialBackOffPolicy(final Builder builder) {
        super();
        this.exponentialBackOff = builder.exponentialBackOffBuilder.build();
    }
    
    @Override
    public boolean isBackOffRequired(final int n) {
        switch (n) {
            case 500:
            case 503:
                return true;
            default:
                return false;
        }
    }
    
    @Override
    public final void reset() {
        this.exponentialBackOff.reset();
    }
    
    @Override
    public long getNextBackOffMillis() throws IOException {
        return this.exponentialBackOff.nextBackOffMillis();
    }
    
    public final int getInitialIntervalMillis() {
        return this.exponentialBackOff.getInitialIntervalMillis();
    }
    
    public final double getRandomizationFactor() {
        return this.exponentialBackOff.getRandomizationFactor();
    }
    
    public final int getCurrentIntervalMillis() {
        return this.exponentialBackOff.getCurrentIntervalMillis();
    }
    
    public final double getMultiplier() {
        return this.exponentialBackOff.getMultiplier();
    }
    
    public final int getMaxIntervalMillis() {
        return this.exponentialBackOff.getMaxIntervalMillis();
    }
    
    public final int getMaxElapsedTimeMillis() {
        return this.exponentialBackOff.getMaxElapsedTimeMillis();
    }
    
    public final long getElapsedTimeMillis() {
        return this.exponentialBackOff.getElapsedTimeMillis();
    }
    
    public static Builder builder() {
        return new Builder();
    }
}
