package com.google.api.client.googleapis.util;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Beta;

@Beta
public final class Utils
{
    public static JsonFactory getDefaultJsonFactory() {
        return JsonFactoryInstanceHolder.INSTANCE;
    }
    
    public static HttpTransport getDefaultTransport() {
        return TransportInstanceHolder.INSTANCE;
    }
    
    private Utils() {
        super();
    }
}
