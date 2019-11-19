package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class DeleteEmbeddedObjectRequest extends GenericJson
{
    @Key
    private Integer objectId;
    
    public DeleteEmbeddedObjectRequest() {
        super();
    }
    
    public Integer getObjectId() {
        return this.objectId;
    }
    
    public DeleteEmbeddedObjectRequest setObjectId(final Integer objectId) {
        this.objectId = objectId;
        return this;
    }
    
    @Override
    public DeleteEmbeddedObjectRequest set(final String s, final Object o) {
        return (DeleteEmbeddedObjectRequest)super.set(s, o);
    }
    
    @Override
    public DeleteEmbeddedObjectRequest clone() {
        return (DeleteEmbeddedObjectRequest)super.clone();
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
