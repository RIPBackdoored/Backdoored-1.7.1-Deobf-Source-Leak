package com.google.api.client.json.jackson2;

import com.fasterxml.jackson.core.JsonToken;
import java.io.InputStream;
import com.google.api.client.util.Preconditions;
import com.google.api.client.json.JsonParser;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonEncoding;
import java.nio.charset.Charset;
import java.io.OutputStream;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.api.client.json.JsonFactory;

public final class JacksonFactory extends JsonFactory
{
    private final com.fasterxml.jackson.core.JsonFactory factory;
    
    public JacksonFactory() {
        super();
        (this.factory = new com.fasterxml.jackson.core.JsonFactory()).configure(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT, false);
    }
    
    public static JacksonFactory getDefaultInstance() {
        return InstanceHolder.INSTANCE;
    }
    
    @Override
    public com.google.api.client.json.JsonGenerator createJsonGenerator(final OutputStream outputStream, final Charset charset) throws IOException {
        return new JacksonGenerator(this, this.factory.createJsonGenerator(outputStream, JsonEncoding.UTF8));
    }
    
    @Override
    public com.google.api.client.json.JsonGenerator createJsonGenerator(final Writer writer) throws IOException {
        return new JacksonGenerator(this, this.factory.createJsonGenerator(writer));
    }
    
    @Override
    public JsonParser createJsonParser(final Reader reader) throws IOException {
        Preconditions.<Reader>checkNotNull(reader);
        return new JacksonParser(this, this.factory.createJsonParser(reader));
    }
    
    @Override
    public JsonParser createJsonParser(final InputStream inputStream) throws IOException {
        Preconditions.<InputStream>checkNotNull(inputStream);
        return new JacksonParser(this, this.factory.createJsonParser(inputStream));
    }
    
    @Override
    public JsonParser createJsonParser(final InputStream inputStream, final Charset charset) throws IOException {
        Preconditions.<InputStream>checkNotNull(inputStream);
        return new JacksonParser(this, this.factory.createJsonParser(inputStream));
    }
    
    @Override
    public JsonParser createJsonParser(final String s) throws IOException {
        Preconditions.<String>checkNotNull(s);
        return new JacksonParser(this, this.factory.createJsonParser(s));
    }
    
    static com.google.api.client.json.JsonToken convert(final JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (jsonToken) {
            case END_ARRAY:
                return com.google.api.client.json.JsonToken.END_ARRAY;
            case START_ARRAY:
                return com.google.api.client.json.JsonToken.START_ARRAY;
            case END_OBJECT:
                return com.google.api.client.json.JsonToken.END_OBJECT;
            case START_OBJECT:
                return com.google.api.client.json.JsonToken.START_OBJECT;
            case VALUE_FALSE:
                return com.google.api.client.json.JsonToken.VALUE_FALSE;
            case VALUE_TRUE:
                return com.google.api.client.json.JsonToken.VALUE_TRUE;
            case VALUE_NULL:
                return com.google.api.client.json.JsonToken.VALUE_NULL;
            case VALUE_STRING:
                return com.google.api.client.json.JsonToken.VALUE_STRING;
            case VALUE_NUMBER_FLOAT:
                return com.google.api.client.json.JsonToken.VALUE_NUMBER_FLOAT;
            case VALUE_NUMBER_INT:
                return com.google.api.client.json.JsonToken.VALUE_NUMBER_INT;
            case FIELD_NAME:
                return com.google.api.client.json.JsonToken.FIELD_NAME;
            default:
                return com.google.api.client.json.JsonToken.NOT_AVAILABLE;
        }
    }
}
