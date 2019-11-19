package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class CreateDeveloperMetadataRequest extends GenericJson
{
    @Key
    private DeveloperMetadata developerMetadata;
    
    public CreateDeveloperMetadataRequest() {
        super();
    }
    
    public DeveloperMetadata getDeveloperMetadata() {
        return this.developerMetadata;
    }
    
    public CreateDeveloperMetadataRequest setDeveloperMetadata(final DeveloperMetadata developerMetadata) {
        this.developerMetadata = developerMetadata;
        return this;
    }
    
    @Override
    public CreateDeveloperMetadataRequest set(final String s, final Object o) {
        return (CreateDeveloperMetadataRequest)super.set(s, o);
    }
    
    @Override
    public CreateDeveloperMetadataRequest clone() {
        return (CreateDeveloperMetadataRequest)super.clone();
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
