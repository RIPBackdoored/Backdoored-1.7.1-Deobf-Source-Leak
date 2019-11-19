package com.google.api.client.testing.http;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Beta;

@Beta
public final class HttpTesting
{
    public static final String SIMPLE_URL = "http://google.com/";
    public static final GenericUrl SIMPLE_GENERIC_URL;
    
    private HttpTesting() {
        super();
    }
    
    static {
        SIMPLE_GENERIC_URL = new GenericUrl("http://google.com/");
    }
}
