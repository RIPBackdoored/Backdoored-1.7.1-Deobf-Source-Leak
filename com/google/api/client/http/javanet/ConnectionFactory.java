package com.google.api.client.http.javanet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public interface ConnectionFactory
{
    HttpURLConnection openConnection(final URL p0) throws IOException, ClassCastException;
}
