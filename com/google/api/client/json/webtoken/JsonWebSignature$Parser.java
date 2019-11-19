package com.google.api.client.json.webtoken;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Base64;
import com.google.api.client.util.Preconditions;
import com.google.api.client.json.JsonFactory;

public static final class Parser
{
    private final JsonFactory jsonFactory;
    private Class<? extends Header> headerClass;
    private Class<? extends Payload> payloadClass;
    
    public Parser(final JsonFactory jsonFactory) {
        super();
        this.headerClass = Header.class;
        this.payloadClass = Payload.class;
        this.jsonFactory = Preconditions.<JsonFactory>checkNotNull(jsonFactory);
    }
    
    public Class<? extends Header> getHeaderClass() {
        return this.headerClass;
    }
    
    public Parser setHeaderClass(final Class<? extends Header> headerClass) {
        this.headerClass = headerClass;
        return this;
    }
    
    public Class<? extends Payload> getPayloadClass() {
        return this.payloadClass;
    }
    
    public Parser setPayloadClass(final Class<? extends Payload> payloadClass) {
        this.payloadClass = payloadClass;
        return this;
    }
    
    public JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }
    
    public JsonWebSignature parse(final String s) throws IOException {
        final int index = s.indexOf(46);
        Preconditions.checkArgument(index != -1);
        final byte[] decodeBase64 = Base64.decodeBase64(s.substring(0, index));
        final int index2 = s.indexOf(46, index + 1);
        Preconditions.checkArgument(index2 != -1);
        Preconditions.checkArgument(s.indexOf(46, index2 + 1) == -1);
        final byte[] decodeBase65 = Base64.decodeBase64(s.substring(index + 1, index2));
        final byte[] decodeBase66 = Base64.decodeBase64(s.substring(index2 + 1));
        final byte[] bytesUtf8 = StringUtils.getBytesUtf8(s.substring(0, index2));
        final Header header = this.jsonFactory.<Header>fromInputStream(new ByteArrayInputStream(decodeBase64), this.headerClass);
        Preconditions.checkArgument(header.getAlgorithm() != null);
        return new JsonWebSignature(header, this.jsonFactory.<Payload>fromInputStream(new ByteArrayInputStream(decodeBase65), this.payloadClass), decodeBase66, bytesUtf8);
    }
}
