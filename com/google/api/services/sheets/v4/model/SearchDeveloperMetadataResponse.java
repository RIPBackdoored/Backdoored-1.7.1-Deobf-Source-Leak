package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class SearchDeveloperMetadataResponse extends GenericJson
{
    @Key
    private List<MatchedDeveloperMetadata> matchedDeveloperMetadata;
    
    public SearchDeveloperMetadataResponse() {
        super();
    }
    
    public List<MatchedDeveloperMetadata> getMatchedDeveloperMetadata() {
        return this.matchedDeveloperMetadata;
    }
    
    public SearchDeveloperMetadataResponse setMatchedDeveloperMetadata(final List<MatchedDeveloperMetadata> matchedDeveloperMetadata) {
        this.matchedDeveloperMetadata = matchedDeveloperMetadata;
        return this;
    }
    
    @Override
    public SearchDeveloperMetadataResponse set(final String s, final Object o) {
        return (SearchDeveloperMetadataResponse)super.set(s, o);
    }
    
    @Override
    public SearchDeveloperMetadataResponse clone() {
        return (SearchDeveloperMetadataResponse)super.clone();
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
        Data.<Object>nullOf(MatchedDeveloperMetadata.class);
    }
}
