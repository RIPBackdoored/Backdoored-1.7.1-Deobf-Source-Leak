package com.google.api.client.util;

import java.io.Reader;
import java.lang.reflect.Type;
import java.io.IOException;
import java.nio.charset.Charset;
import java.io.InputStream;

public interface ObjectParser
{
     <T> T parseAndClose(final InputStream p0, final Charset p1, final Class<T> p2) throws IOException;
    
    Object parseAndClose(final InputStream p0, final Charset p1, final Type p2) throws IOException;
    
     <T> T parseAndClose(final Reader p0, final Class<T> p1) throws IOException;
    
    Object parseAndClose(final Reader p0, final Type p1) throws IOException;
}
