package com.google.api.client.http;

import java.io.IOException;

public interface HttpRequestInitializer
{
    void initialize(final HttpRequest p0) throws IOException;
}
