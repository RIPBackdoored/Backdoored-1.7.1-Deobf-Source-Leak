package com.google.api.client.json;

import java.io.IOException;
import com.google.api.client.util.Throwables;
import com.google.api.client.util.GenericData;

public class GenericJson extends GenericData implements Cloneable
{
    private JsonFactory jsonFactory;
    
    public GenericJson() {
        super();
    }
    
    public final JsonFactory getFactory() {
        return this.jsonFactory;
    }
    
    public final void setFactory(final JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }
    
    @Override
    public String toString() {
        if (this.jsonFactory != null) {
            try {
                return this.jsonFactory.toString(this);
            }
            catch (IOException ex) {
                throw Throwables.propagate(ex);
            }
        }
        return super.toString();
    }
    
    public String toPrettyString() throws IOException {
        if (this.jsonFactory != null) {
            return this.jsonFactory.toPrettyString(this);
        }
        return super.toString();
    }
    
    @Override
    public GenericJson clone() {
        return (GenericJson)super.clone();
    }
    
    @Override
    public GenericJson set(final String s, final Object o) {
        return (GenericJson)super.set(s, o);
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
