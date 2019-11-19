package com.google.api.client.googleapis.services;

import java.io.IOException;

public interface GoogleClientRequestInitializer
{
    void initialize(final AbstractGoogleClientRequest<?> p0) throws IOException;
}
