package com.google.api.client.json;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import java.util.Collection;

public static class Builder
{
    final JsonFactory jsonFactory;
    Collection<String> wrapperKeys;
    
    public Builder(final JsonFactory jsonFactory) {
        super();
        this.wrapperKeys = (Collection<String>)Sets.<Object>newHashSet();
        this.jsonFactory = Preconditions.<JsonFactory>checkNotNull(jsonFactory);
    }
    
    public JsonObjectParser build() {
        return new JsonObjectParser(this);
    }
    
    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }
    
    public final Collection<String> getWrapperKeys() {
        return this.wrapperKeys;
    }
    
    public Builder setWrapperKeys(final Collection<String> wrapperKeys) {
        this.wrapperKeys = wrapperKeys;
        return this;
    }
}
