package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class UpdateDeveloperMetadataResponse extends GenericJson
{
    @Key
    private List<DeveloperMetadata> developerMetadata;
    
    public UpdateDeveloperMetadataResponse() {
        super();
    }
    
    public List<DeveloperMetadata> getDeveloperMetadata() {
        return this.developerMetadata;
    }
    
    public UpdateDeveloperMetadataResponse setDeveloperMetadata(final List<DeveloperMetadata> developerMetadata) {
        this.developerMetadata = developerMetadata;
        return this;
    }
    
    @Override
    public UpdateDeveloperMetadataResponse set(final String s, final Object o) {
        return (UpdateDeveloperMetadataResponse)super.set(s, o);
    }
    
    @Override
    public UpdateDeveloperMetadataResponse clone() {
        return (UpdateDeveloperMetadataResponse)super.clone();
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
