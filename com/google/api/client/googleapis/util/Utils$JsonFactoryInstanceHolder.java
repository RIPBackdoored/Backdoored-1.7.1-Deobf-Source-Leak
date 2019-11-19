package com.google.api.client.googleapis.util;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;

private static class JsonFactoryInstanceHolder
{
    static final JsonFactory INSTANCE;
    
    private JsonFactoryInstanceHolder() {
        super();
    }
    
    static {
        INSTANCE = new JacksonFactory();
    }
}
