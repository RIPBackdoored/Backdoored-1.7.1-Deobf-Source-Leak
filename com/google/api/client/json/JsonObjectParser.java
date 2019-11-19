package com.google.api.client.json;

import com.google.api.client.util.Sets;
import com.google.api.client.util.Preconditions;
import java.util.Collections;
import java.io.Reader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.google.api.client.util.ObjectParser;

public class JsonObjectParser implements ObjectParser
{
    private final JsonFactory jsonFactory;
    private final Set<String> wrapperKeys;
    
    public JsonObjectParser(final JsonFactory jsonFactory) {
        this(new Builder(jsonFactory));
    }
    
    protected JsonObjectParser(final Builder builder) {
        super();
        this.jsonFactory = builder.jsonFactory;
        this.wrapperKeys = new HashSet<String>(builder.wrapperKeys);
    }
    
    @Override
    public <T> T parseAndClose(final InputStream inputStream, final Charset charset, final Class<T> clazz) throws IOException {
        return (T)this.parseAndClose(inputStream, charset, (Type)clazz);
    }
    
    @Override
    public Object parseAndClose(final InputStream inputStream, final Charset charset, final Type type) throws IOException {
        final JsonParser jsonParser = this.jsonFactory.createJsonParser(inputStream, charset);
        this.initializeParser(jsonParser);
        return jsonParser.parse(type, true);
    }
    
    @Override
    public <T> T parseAndClose(final Reader reader, final Class<T> clazz) throws IOException {
        return (T)this.parseAndClose(reader, (Type)clazz);
    }
    
    @Override
    public Object parseAndClose(final Reader reader, final Type type) throws IOException {
        final JsonParser jsonParser = this.jsonFactory.createJsonParser(reader);
        this.initializeParser(jsonParser);
        return jsonParser.parse(type, true);
    }
    
    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }
    
    public Set<String> getWrapperKeys() {
        return Collections.<String>unmodifiableSet((Set<? extends String>)this.wrapperKeys);
    }
    
    private void initializeParser(final JsonParser jsonParser) throws IOException {
        if (this.wrapperKeys.isEmpty()) {
            return;
        }
        boolean b = true;
        try {
            Preconditions.checkArgument(jsonParser.skipToKey(this.wrapperKeys) != null && jsonParser.getCurrentToken() != JsonToken.END_OBJECT, "wrapper key(s) not found: %s", this.wrapperKeys);
            b = false;
        }
        finally {
            if (b) {
                jsonParser.close();
            }
        }
    }
}
