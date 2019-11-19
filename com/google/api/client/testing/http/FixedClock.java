package com.google.api.client.testing.http;

import java.util.concurrent.atomic.AtomicLong;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;

@Beta
public class FixedClock implements Clock
{
    private AtomicLong currentTime;
    
    public FixedClock() {
        this(0L);
    }
    
    public FixedClock(final long n) {
        super();
        this.currentTime = new AtomicLong(n);
    }
    
    public FixedClock setTime(final long n) {
        this.currentTime.set(n);
        return this;
    }
    
    @Override
    public long currentTimeMillis() {
        return this.currentTime.get();
    }
}
