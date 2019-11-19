package com.google.api.client.util;

public static class Builder
{
    int initialIntervalMillis;
    double randomizationFactor;
    double multiplier;
    int maxIntervalMillis;
    int maxElapsedTimeMillis;
    NanoClock nanoClock;
    
    public Builder() {
        super();
        this.initialIntervalMillis = 500;
        this.randomizationFactor = 0.5;
        this.multiplier = 1.5;
        this.maxIntervalMillis = 60000;
        this.maxElapsedTimeMillis = 900000;
        this.nanoClock = NanoClock.SYSTEM;
    }
    
    public ExponentialBackOff build() {
        return new ExponentialBackOff(this);
    }
    
    public final int getInitialIntervalMillis() {
        return this.initialIntervalMillis;
    }
    
    public Builder setInitialIntervalMillis(final int initialIntervalMillis) {
        this.initialIntervalMillis = initialIntervalMillis;
        return this;
    }
    
    public final double getRandomizationFactor() {
        return this.randomizationFactor;
    }
    
    public Builder setRandomizationFactor(final double randomizationFactor) {
        this.randomizationFactor = randomizationFactor;
        return this;
    }
    
    public final double getMultiplier() {
        return this.multiplier;
    }
    
    public Builder setMultiplier(final double multiplier) {
        this.multiplier = multiplier;
        return this;
    }
    
    public final int getMaxIntervalMillis() {
        return this.maxIntervalMillis;
    }
    
    public Builder setMaxIntervalMillis(final int maxIntervalMillis) {
        this.maxIntervalMillis = maxIntervalMillis;
        return this;
    }
    
    public final int getMaxElapsedTimeMillis() {
        return this.maxElapsedTimeMillis;
    }
    
    public Builder setMaxElapsedTimeMillis(final int maxElapsedTimeMillis) {
        this.maxElapsedTimeMillis = maxElapsedTimeMillis;
        return this;
    }
    
    public final NanoClock getNanoClock() {
        return this.nanoClock;
    }
    
    public Builder setNanoClock(final NanoClock nanoClock) {
        this.nanoClock = Preconditions.<NanoClock>checkNotNull(nanoClock);
        return this;
    }
}
