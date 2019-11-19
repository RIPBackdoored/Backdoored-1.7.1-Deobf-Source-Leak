package com.google.api.client.util;

import java.io.IOException;

public class ExponentialBackOff implements BackOff
{
    public static final int DEFAULT_INITIAL_INTERVAL_MILLIS = 500;
    public static final double DEFAULT_RANDOMIZATION_FACTOR = 0.5;
    public static final double DEFAULT_MULTIPLIER = 1.5;
    public static final int DEFAULT_MAX_INTERVAL_MILLIS = 60000;
    public static final int DEFAULT_MAX_ELAPSED_TIME_MILLIS = 900000;
    private int currentIntervalMillis;
    private final int initialIntervalMillis;
    private final double randomizationFactor;
    private final double multiplier;
    private final int maxIntervalMillis;
    long startTimeNanos;
    private final int maxElapsedTimeMillis;
    private final NanoClock nanoClock;
    
    public ExponentialBackOff() {
        this(new Builder());
    }
    
    protected ExponentialBackOff(final Builder builder) {
        super();
        this.initialIntervalMillis = builder.initialIntervalMillis;
        this.randomizationFactor = builder.randomizationFactor;
        this.multiplier = builder.multiplier;
        this.maxIntervalMillis = builder.maxIntervalMillis;
        this.maxElapsedTimeMillis = builder.maxElapsedTimeMillis;
        this.nanoClock = builder.nanoClock;
        Preconditions.checkArgument(this.initialIntervalMillis > 0);
        Preconditions.checkArgument(0.0 <= this.randomizationFactor && this.randomizationFactor < 1.0);
        Preconditions.checkArgument(this.multiplier >= 1.0);
        Preconditions.checkArgument(this.maxIntervalMillis >= this.initialIntervalMillis);
        Preconditions.checkArgument(this.maxElapsedTimeMillis > 0);
        this.reset();
    }
    
    @Override
    public final void reset() {
        this.currentIntervalMillis = this.initialIntervalMillis;
        this.startTimeNanos = this.nanoClock.nanoTime();
    }
    
    @Override
    public long nextBackOffMillis() throws IOException {
        if (this.getElapsedTimeMillis() > this.maxElapsedTimeMillis) {
            return -1L;
        }
        final int randomValueFromInterval = getRandomValueFromInterval(this.randomizationFactor, Math.random(), this.currentIntervalMillis);
        this.incrementCurrentInterval();
        return randomValueFromInterval;
    }
    
    static int getRandomValueFromInterval(final double n, final double n2, final int n3) {
        final double n4 = n * n3;
        final double n5 = n3 - n4;
        return (int)(n5 + n2 * (n3 + n4 - n5 + 1.0));
    }
    
    public final int getInitialIntervalMillis() {
        return this.initialIntervalMillis;
    }
    
    public final double getRandomizationFactor() {
        return this.randomizationFactor;
    }
    
    public final int getCurrentIntervalMillis() {
        return this.currentIntervalMillis;
    }
    
    public final double getMultiplier() {
        return this.multiplier;
    }
    
    public final int getMaxIntervalMillis() {
        return this.maxIntervalMillis;
    }
    
    public final int getMaxElapsedTimeMillis() {
        return this.maxElapsedTimeMillis;
    }
    
    public final long getElapsedTimeMillis() {
        return (this.nanoClock.nanoTime() - this.startTimeNanos) / 1000000L;
    }
    
    private void incrementCurrentInterval() {
        if (this.currentIntervalMillis >= this.maxIntervalMillis / this.multiplier) {
            this.currentIntervalMillis = this.maxIntervalMillis;
        }
        else {
            this.currentIntervalMillis *= (int)this.multiplier;
        }
    }
}
