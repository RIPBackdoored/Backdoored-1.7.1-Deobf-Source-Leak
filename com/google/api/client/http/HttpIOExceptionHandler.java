package com.google.api.client.http;

import java.io.IOException;
import com.google.api.client.util.Beta;

@Beta
public interface HttpIOExceptionHandler
{
    boolean handleIOException(final HttpRequest p0, final boolean p1) throws IOException;
}
