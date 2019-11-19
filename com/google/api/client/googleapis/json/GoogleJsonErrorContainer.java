package com.google.api.client.googleapis.json;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public class GoogleJsonErrorContainer extends GenericJson
{
    @Key
    private GoogleJsonError error;
    
    public GoogleJsonErrorContainer() {
        super();
    }
    
    public final GoogleJsonError getError() {
        return this.error;
    }
    
    public final void setError(final GoogleJsonError error) {
        this.error = error;
    }
    
    @Override
    public GoogleJsonErrorContainer set(final String s, final Object o) {
        return (GoogleJsonErrorContainer)super.set(s, o);
    }
    
    @Override
    public GoogleJsonErrorContainer clone() {
        return (GoogleJsonErrorContainer)super.clone();
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
}
