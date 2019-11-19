package com.google.api.client.json.jackson2;

import com.google.api.client.json.JsonFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.IOException;
import com.google.api.client.json.JsonGenerator;

final class JacksonGenerator extends JsonGenerator
{
    private final com.fasterxml.jackson.core.JsonGenerator generator;
    private final JacksonFactory factory;
    
    @Override
    public JacksonFactory getFactory() {
        return this.factory;
    }
    
    JacksonGenerator(final JacksonFactory factory, final com.fasterxml.jackson.core.JsonGenerator generator) {
        super();
        this.factory = factory;
        this.generator = generator;
    }
    
    @Override
    public void flush() throws IOException {
        this.generator.flush();
    }
    
    @Override
    public void close() throws IOException {
        this.generator.close();
    }
    
    @Override
    public void writeBoolean(final boolean b) throws IOException {
        this.generator.writeBoolean(b);
    }
    
    @Override
    public void writeEndArray() throws IOException {
        this.generator.writeEndArray();
    }
    
    @Override
    public void writeEndObject() throws IOException {
        this.generator.writeEndObject();
    }
    
    @Override
    public void writeFieldName(final String s) throws IOException {
        this.generator.writeFieldName(s);
    }
    
    @Override
    public void writeNull() throws IOException {
        this.generator.writeNull();
    }
    
    @Override
    public void writeNumber(final int n) throws IOException {
        this.generator.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final long n) throws IOException {
        this.generator.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final BigInteger bigInteger) throws IOException {
        this.generator.writeNumber(bigInteger);
    }
    
    @Override
    public void writeNumber(final double n) throws IOException {
        this.generator.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final float n) throws IOException {
        this.generator.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final BigDecimal bigDecimal) throws IOException {
        this.generator.writeNumber(bigDecimal);
    }
    
    @Override
    public void writeNumber(final String s) throws IOException {
        this.generator.writeNumber(s);
    }
    
    @Override
    public void writeStartArray() throws IOException {
        this.generator.writeStartArray();
    }
    
    @Override
    public void writeStartObject() throws IOException {
        this.generator.writeStartObject();
    }
    
    @Override
    public void writeString(final String s) throws IOException {
        this.generator.writeString(s);
    }
    
    @Override
    public void enablePrettyPrint() throws IOException {
        this.generator.useDefaultPrettyPrinter();
    }
    
    @Override
    public /* bridge */ JsonFactory getFactory() {
        return this.getFactory();
    }
}
