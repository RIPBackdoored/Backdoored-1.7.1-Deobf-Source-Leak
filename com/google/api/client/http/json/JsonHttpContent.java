package com.google.api.client.http.json;

import com.google.api.client.http.HttpMediaType;
import java.io.IOException;
import com.google.api.client.json.JsonGenerator;
import java.io.OutputStream;
import com.google.api.client.util.Preconditions;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.AbstractHttpContent;

public class JsonHttpContent extends AbstractHttpContent
{
    private final Object data;
    private final JsonFactory jsonFactory;
    private String wrapperKey;
    
    public JsonHttpContent(final JsonFactory jsonFactory, final Object o) {
        super("application/json; charset=UTF-8");
        this.jsonFactory = Preconditions.<JsonFactory>checkNotNull(jsonFactory);
        this.data = Preconditions.<Object>checkNotNull(o);
    }
    
    @Override
    public void writeTo(final OutputStream outputStream) throws IOException {
        final JsonGenerator jsonGenerator = this.jsonFactory.createJsonGenerator(outputStream, this.getCharset());
        if (this.wrapperKey != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName(this.wrapperKey);
        }
        jsonGenerator.serialize(this.data);
        if (this.wrapperKey != null) {
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.flush();
    }
    
    @Override
    public JsonHttpContent setMediaType(final HttpMediaType mediaType) {
        super.setMediaType(mediaType);
        return this;
    }
    
    public final Object getData() {
        return this.data;
    }
    
    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }
    
    public final String getWrapperKey() {
        return this.wrapperKey;
    }
    
    public JsonHttpContent setWrapperKey(final String wrapperKey) {
        this.wrapperKey = wrapperKey;
        return this;
    }
    
    @Override
    public /* bridge */ AbstractHttpContent setMediaType(final HttpMediaType mediaType) {
        return this.setMediaType(mediaType);
    }
}
