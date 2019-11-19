package com.google.api.client.googleapis.auth.clientlogin;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.FieldInfo;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.api.client.util.Types;
import com.google.api.client.util.ClassInfo;
import java.io.IOException;
import java.io.InputStream;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ObjectParser;

@Beta
final class AuthKeyValueParser implements ObjectParser
{
    public static final AuthKeyValueParser INSTANCE;
    
    public String getContentType() {
        return "text/plain";
    }
    
    public <T> T parse(final HttpResponse httpResponse, final Class<T> clazz) throws IOException {
        httpResponse.setContentLoggingLimit(0);
        final InputStream content = httpResponse.getContent();
        try {
            return this.<T>parse(content, clazz);
        }
        finally {
            content.close();
        }
    }
    
    public <T> T parse(final InputStream inputStream, final Class<T> clazz) throws IOException {
        final ClassInfo of = ClassInfo.of(clazz);
        final T instance = Types.<T>newInstance(clazz);
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            final String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            final int index = line.indexOf(61);
            final String substring = line.substring(0, index);
            final String substring2 = line.substring(index + 1);
            final Field field = of.getField(substring);
            if (field != null) {
                final Class<?> type = field.getType();
                Serializable value;
                if (type == Boolean.TYPE || type == Boolean.class) {
                    value = Boolean.valueOf(substring2);
                }
                else {
                    value = substring2;
                }
                FieldInfo.setFieldValue(field, instance, value);
            }
            else if (GenericData.class.isAssignableFrom(clazz)) {
                ((GenericData)instance).set(substring, substring2);
            }
            else {
                if (!Map.class.isAssignableFrom(clazz)) {
                    continue;
                }
                ((Map<String, String>)instance).put(substring, substring2);
            }
        }
        return instance;
    }
    
    private AuthKeyValueParser() {
        super();
    }
    
    public <T> T parseAndClose(final InputStream inputStream, final Charset charset, final Class<T> clazz) throws IOException {
        return this.<T>parseAndClose(new InputStreamReader(inputStream, charset), clazz);
    }
    
    public Object parseAndClose(final InputStream inputStream, final Charset charset, final Type type) {
        throw new UnsupportedOperationException("Type-based parsing is not yet supported -- use Class<T> instead");
    }
    
    public <T> T parseAndClose(final Reader reader, final Class<T> clazz) throws IOException {
        try {
            final ClassInfo of = ClassInfo.of(clazz);
            final T instance = Types.<T>newInstance(clazz);
            final BufferedReader bufferedReader = new BufferedReader(reader);
            while (true) {
                final String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                final int index = line.indexOf(61);
                final String substring = line.substring(0, index);
                final String substring2 = line.substring(index + 1);
                final Field field = of.getField(substring);
                if (field != null) {
                    final Class<?> type = field.getType();
                    Serializable value;
                    if (type == Boolean.TYPE || type == Boolean.class) {
                        value = Boolean.valueOf(substring2);
                    }
                    else {
                        value = substring2;
                    }
                    FieldInfo.setFieldValue(field, instance, value);
                }
                else if (GenericData.class.isAssignableFrom(clazz)) {
                    ((GenericData)instance).set(substring, substring2);
                }
                else {
                    if (!Map.class.isAssignableFrom(clazz)) {
                        continue;
                    }
                    ((Map<String, String>)instance).put(substring, substring2);
                }
            }
            return instance;
        }
        finally {
            reader.close();
        }
    }
    
    public Object parseAndClose(final Reader reader, final Type type) {
        throw new UnsupportedOperationException("Type-based parsing is not yet supported -- use Class<T> instead");
    }
    
    static {
        INSTANCE = new AuthKeyValueParser();
    }
}
