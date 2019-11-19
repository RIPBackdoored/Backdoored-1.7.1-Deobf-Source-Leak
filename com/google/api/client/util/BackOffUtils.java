package com.google.api.client.util;

import java.io.IOException;

@Beta
public final class BackOffUtils
{
    public static boolean next(final Sleeper sleeper, final BackOff backOff) throws InterruptedException, IOException {
        final long nextBackOffMillis = backOff.nextBackOffMillis();
        if (nextBackOffMillis == -1L) {
            return false;
        }
        sleeper.sleep(nextBackOffMillis);
        return true;
    }
    
    private BackOffUtils() {
        super();
    }
}
