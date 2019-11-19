package com.google.api.client.http;

import java.io.IOException;

public interface HttpExecuteInterceptor
{
    void intercept(final HttpRequest p0) throws IOException;
}
