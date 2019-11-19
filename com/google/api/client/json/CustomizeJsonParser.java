package com.google.api.client.json;

import java.util.Collection;
import java.lang.reflect.Field;
import com.google.api.client.util.Beta;

@Beta
public class CustomizeJsonParser
{
    public CustomizeJsonParser() {
        super();
    }
    
    public boolean stopAt(final Object o, final String s) {
        return false;
    }
    
    public void handleUnrecognizedKey(final Object o, final String s) {
    }
    
    public Collection<Object> newInstanceForArray(final Object o, final Field field) {
        return null;
    }
    
    public Object newInstanceForObject(final Object o, final Class<?> clazz) {
        return null;
    }
}
