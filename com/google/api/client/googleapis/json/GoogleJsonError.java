package com.google.api.client.googleapis.json;

import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public class GoogleJsonError extends GenericJson
{
    @Key
    private List<ErrorInfo> errors;
    @Key
    private int code;
    @Key
    private String message;
    
    public GoogleJsonError() {
        super();
    }
    
    public static GoogleJsonError parse(final JsonFactory jsonFactory, final HttpResponse httpResponse) throws IOException {
        return new JsonObjectParser.Builder(jsonFactory).setWrapperKeys(Collections.<String>singleton("error")).build().<GoogleJsonError>parseAndClose(httpResponse.getContent(), httpResponse.getContentCharset(), GoogleJsonError.class);
    }
    
    public final List<ErrorInfo> getErrors() {
        return this.errors;
    }
    
    public final void setErrors(final List<ErrorInfo> errors) {
        this.errors = errors;
    }
    
    public final int getCode() {
        return this.code;
    }
    
    public final void setCode(final int code) {
        this.code = code;
    }
    
    public final String getMessage() {
        return this.message;
    }
    
    public final void setMessage(final String message) {
        this.message = message;
    }
    
    @Override
    public GoogleJsonError set(final String s, final Object o) {
        return (GoogleJsonError)super.set(s, o);
    }
    
    @Override
    public GoogleJsonError clone() {
        return (GoogleJsonError)super.clone();
    }
    
    @Override
    public /* bridge */ GenericJson set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ GenericJson clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ GenericData clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    public /* bridge */ Object clone() throws CloneNotSupportedException {
        return this.clone();
    }
    
    static {
        Data.<Object>nullOf(ErrorInfo.class);
    }
}
