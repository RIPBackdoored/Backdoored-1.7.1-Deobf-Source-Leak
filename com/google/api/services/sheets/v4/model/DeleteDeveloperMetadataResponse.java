package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class DeleteDeveloperMetadataResponse extends GenericJson
{
    @Key
    private List<DeveloperMetadata> deletedDeveloperMetadata;
    
    public DeleteDeveloperMetadataResponse() {
        super();
    }
    
    public List<DeveloperMetadata> getDeletedDeveloperMetadata() {
        return this.deletedDeveloperMetadata;
    }
    
    public DeleteDeveloperMetadataResponse setDeletedDeveloperMetadata(final List<DeveloperMetadata> deletedDeveloperMetadata) {
        this.deletedDeveloperMetadata = deletedDeveloperMetadata;
        return this;
    }
    
    @Override
    public DeleteDeveloperMetadataResponse set(final String s, final Object o) {
        return (DeleteDeveloperMetadataResponse)super.set(s, o);
    }
    
    @Override
    public DeleteDeveloperMetadataResponse clone() {
        return (DeleteDeveloperMetadataResponse)super.clone();
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
