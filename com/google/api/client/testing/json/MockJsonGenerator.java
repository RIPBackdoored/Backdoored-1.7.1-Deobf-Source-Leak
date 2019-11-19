package com.google.api.client.testing.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.IOException;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Beta;
import com.google.api.client.json.JsonGenerator;

@Beta
public class MockJsonGenerator extends JsonGenerator
{
    private final JsonFactory factory;
    
    MockJsonGenerator(final JsonFactory factory) {
        super();
        this.factory = factory;
    }
    
    @Override
    public JsonFactory getFactory() {
        return this.factory;
    }
    
    @Override
    public void flush() throws IOException {
    }
    
    @Override
    public void close() throws IOException {
    }
    
    @Override
    public void writeStartArray() throws IOException {
    }
    
    @Override
    public void writeEndArray() throws IOException {
    }
    
    @Override
    public void writeStartObject() throws IOException {
    }
    
    @Override
    public void writeEndObject() throws IOException {
    }
    
    @Override
    public void writeFieldName(final String s) throws IOException {
    }
    
    @Override
    public void writeNull() throws IOException {
    }
    
    @Override
    public void writeString(final String s) throws IOException {
    }
    
    @Override
    public void writeBoolean(final boolean b) throws IOException {
    }
    
    @Override
    public void writeNumber(final int n) throws IOException {
    }
    
    @Override
    public void writeNumber(final long n) throws IOException {
    }
    
    @Override
    public void writeNumber(final BigInteger bigInteger) throws IOException {
    }
    
    @Override
    public void writeNumber(final float n) throws IOException {
    }
    
    @Override
    public void writeNumber(final double n) throws IOException {
    }
    
    @Override
    public void writeNumber(final BigDecimal bigDecimal) throws IOException {
    }
    
    @Override
    public void writeNumber(final String s) throws IOException {
    }
}
