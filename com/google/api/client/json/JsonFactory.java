package com.google.api.client.json;

import com.google.api.client.util.Charsets;
import java.io.ByteArrayOutputStream;
import java.io.Writer;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.io.IOException;
import java.io.InputStream;

public abstract class JsonFactory
{
    public JsonFactory() {
        super();
    }
    
    public abstract JsonParser createJsonParser(final InputStream p0) throws IOException;
    
    public abstract JsonParser createJsonParser(final InputStream p0, final Charset p1) throws IOException;
    
    public abstract JsonParser createJsonParser(final String p0) throws IOException;
    
    public abstract JsonParser createJsonParser(final Reader p0) throws IOException;
    
    public abstract JsonGenerator createJsonGenerator(final OutputStream p0, final Charset p1) throws IOException;
    
    public abstract JsonGenerator createJsonGenerator(final Writer p0) throws IOException;
    
    public final JsonObjectParser createJsonObjectParser() {
        return new JsonObjectParser(this);
    }
    
    public final String toString(final Object o) throws IOException {
        return this.toString(o, false);
    }
    
    public final String toPrettyString(final Object o) throws IOException {
        return this.toString(o, true);
    }
    
    public final byte[] toByteArray(final Object o) throws IOException {
        return this.toByteStream(o, false).toByteArray();
    }
    
    private String toString(final Object o, final boolean b) throws IOException {
        return this.toByteStream(o, b).toString("UTF-8");
    }
    
    private ByteArrayOutputStream toByteStream(final Object o, final boolean b) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final JsonGenerator jsonGenerator = this.createJsonGenerator(byteArrayOutputStream, Charsets.UTF_8);
        if (b) {
            jsonGenerator.enablePrettyPrint();
        }
        jsonGenerator.serialize(o);
        jsonGenerator.flush();
        return byteArrayOutputStream;
    }
    
    public final <T> T fromString(final String s, final Class<T> clazz) throws IOException {
        return this.createJsonParser(s).<T>parse(clazz);
    }
    
    public final <T> T fromInputStream(final InputStream inputStream, final Class<T> clazz) throws IOException {
        return this.createJsonParser(inputStream).<T>parseAndClose(clazz);
    }
    
    public final <T> T fromInputStream(final InputStream inputStream, final Charset charset, final Class<T> clazz) throws IOException {
        return this.createJsonParser(inputStream, charset).<T>parseAndClose(clazz);
    }
    
    public final <T> T fromReader(final Reader reader, final Class<T> clazz) throws IOException {
        return this.createJsonParser(reader).<T>parseAndClose(clazz);
    }
}
