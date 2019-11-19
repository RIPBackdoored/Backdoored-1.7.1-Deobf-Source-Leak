package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class CreateDeveloperMetadataResponse extends GenericJson
{
    @Key
    private DeveloperMetadata developerMetadata;
    
    public CreateDeveloperMetadataResponse() {
        super();
    }
    
    public DeveloperMetadata getDeveloperMetadata() {
        return this.developerMetadata;
    }
    
    public CreateDeveloperMetadataResponse setDeveloperMetadata(final DeveloperMetadata developerMetadata) {
        this.developerMetadata = developerMetadata;
        return this;
    }
    
    @Override
    public CreateDeveloperMetadataResponse set(final String s, final Object o) {
        return (CreateDeveloperMetadataResponse)super.set(s, o);
    }
    
    @Override
    public CreateDeveloperMetadataResponse clone() {
        return (CreateDeveloperMetadataResponse)super.clone();
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
