package com.google.api.services.sheets.v4;

import com.google.api.services.sheets.v4.model.SearchDeveloperMetadataResponse;
import com.google.api.client.util.GenericData;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Key;
import com.google.api.services.sheets.v4.model.SearchDeveloperMetadataRequest;
import java.io.IOException;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;

public class DeveloperMetadata
{
    final /* synthetic */ Spreadsheets this$1;
    
    public DeveloperMetadata(final Spreadsheets this$1) {
        this.this$1 = this$1;
        super();
    }
    
    public Get get(final String s, final Integer n) throws IOException {
        final Get get = new Get(s, n);
        this.this$1.this$0.initialize(get);
        return get;
    }
    
    public Search search(final String s, final SearchDeveloperMetadataRequest searchDeveloperMetadataRequest) throws IOException {
        final Search search = new Search(s, searchDeveloperMetadataRequest);
        this.this$1.this$0.initialize(search);
        return search;
    }
}
