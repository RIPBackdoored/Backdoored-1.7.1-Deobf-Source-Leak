package com.google.api.client.http;

import java.io.IOException;
import com.google.api.client.util.Beta;

@Deprecated
@Beta
public interface BackOffPolicy
{
    public static final long STOP = -1L;
    
    boolean isBackOffRequired(final int p0);
    
    void reset();
    
    long getNextBackOffMillis() throws IOException;
}
