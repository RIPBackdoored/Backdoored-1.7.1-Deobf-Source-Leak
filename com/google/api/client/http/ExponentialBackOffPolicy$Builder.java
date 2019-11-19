package com.google.api.client.http;

import com.google.api.client.util.NanoClock;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.Beta;

@Deprecated
@Beta
public static class Builder
{
    final ExponentialBackOff.Builder exponentialBackOffBuilder;
    
    protected Builder() {
        super();
        this.exponentialBackOffBuilder = new ExponentialBackOff.Builder();
    }
    
    public ExponentialBackOffPolicy build() {
        return new ExponentialBackOffPolicy(this);
    }
    
    public final int getInitialIntervalMillis() {
        return this.exponentialBackOffBuilder.getInitialIntervalMillis();
    }
    
    public Builder setInitialIntervalMillis(final int initialIntervalMillis) {
        this.exponentialBackOffBuilder.setInitialIntervalMillis(initialIntervalMillis);
        return this;
    }
    
    public final double getRandomizationFactor() {
        return this.exponentialBackOffBuilder.getRandomizationFactor();
    }
    
    public Builder setRandomizationFactor(final double randomizationFactor) {
        this.exponentialBackOffBuilder.setRandomizationFactor(randomizationFactor);
        return this;
    }
    
    public final double getMultiplier() {
        return this.exponentialBackOffBuilder.getMultiplier();
    }
    
    public Builder setMultiplier(final double multiplier) {
        this.exponentialBackOffBuilder.setMultiplier(multiplier);
        return this;
    }
    
    public final int getMaxIntervalMillis() {
        return this.exponentialBackOffBuilder.getMaxIntervalMillis();
    }
    
    public Builder setMaxIntervalMillis(final int maxIntervalMillis) {
        this.exponentialBackOffBuilder.setMaxIntervalMillis(maxIntervalMillis);
        return this;
    }
    
    public final int getMaxElapsedTimeMillis() {
        return this.exponentialBackOffBuilder.getMaxElapsedTimeMillis();
    }
    
    public Builder setMaxElapsedTimeMillis(final int maxElapsedTimeMillis) {
        this.exponentialBackOffBuilder.setMaxElapsedTimeMillis(maxElapsedTimeMillis);
        return this;
    }
    
    public final NanoClock getNanoClock() {
        return this.exponentialBackOffBuilder.getNanoClock();
    }
    
    public Builder setNanoClock(final NanoClock nanoClock) {
        this.exponentialBackOffBuilder.setNanoClock(nanoClock);
        return this;
    }
}
