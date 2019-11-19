package com.google.api.services.sheets.v4;

import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ClearValuesResponse;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesByDataFilterResponse;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.BatchGetValuesByDataFilterResponse;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import java.util.List;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.BatchClearValuesByDataFilterResponse;
import com.google.api.services.sheets.v4.model.BatchClearValuesResponse;
import com.google.api.client.util.GenericData;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Key;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesByDataFilterRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchGetValuesByDataFilterRequest;
import com.google.api.services.sheets.v4.model.BatchClearValuesByDataFilterRequest;
import com.google.api.services.sheets.v4.model.BatchClearValuesRequest;
import java.io.IOException;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.services.sheets.v4.model.ValueRange;

public class Values
{
    final /* synthetic */ Spreadsheets this$1;
    
    public Values(final Spreadsheets this$1) {
        this.this$1 = this$1;
        super();
    }
    
    public Append append(final String s, final String s2, final ValueRange valueRange) throws IOException {
        final Append append = new Append(s, s2, valueRange);
        this.this$1.this$0.initialize(append);
        return append;
    }
    
    public BatchClear batchClear(final String s, final BatchClearValuesRequest batchClearValuesRequest) throws IOException {
        final BatchClear batchClear = new BatchClear(s, batchClearValuesRequest);
        this.this$1.this$0.initialize(batchClear);
        return batchClear;
    }
    
    public BatchClearByDataFilter batchClearByDataFilter(final String s, final BatchClearValuesByDataFilterRequest batchClearValuesByDataFilterRequest) throws IOException {
        final BatchClearByDataFilter batchClearByDataFilter = new BatchClearByDataFilter(s, batchClearValuesByDataFilterRequest);
        this.this$1.this$0.initialize(batchClearByDataFilter);
        return batchClearByDataFilter;
    }
    
    public BatchGet batchGet(final String s) throws IOException {
        final BatchGet batchGet = new BatchGet(s);
        this.this$1.this$0.initialize(batchGet);
        return batchGet;
    }
    
    public BatchGetByDataFilter batchGetByDataFilter(final String s, final BatchGetValuesByDataFilterRequest batchGetValuesByDataFilterRequest) throws IOException {
        final BatchGetByDataFilter batchGetByDataFilter = new BatchGetByDataFilter(s, batchGetValuesByDataFilterRequest);
        this.this$1.this$0.initialize(batchGetByDataFilter);
        return batchGetByDataFilter;
    }
    
    public BatchUpdate batchUpdate(final String s, final BatchUpdateValuesRequest batchUpdateValuesRequest) throws IOException {
        final BatchUpdate batchUpdate = new BatchUpdate(s, batchUpdateValuesRequest);
        this.this$1.this$0.initialize(batchUpdate);
        return batchUpdate;
    }
    
    public BatchUpdateByDataFilter batchUpdateByDataFilter(final String s, final BatchUpdateValuesByDataFilterRequest batchUpdateValuesByDataFilterRequest) throws IOException {
        final BatchUpdateByDataFilter batchUpdateByDataFilter = new BatchUpdateByDataFilter(s, batchUpdateValuesByDataFilterRequest);
        this.this$1.this$0.initialize(batchUpdateByDataFilter);
        return batchUpdateByDataFilter;
    }
    
    public Clear clear(final String s, final String s2, final ClearValuesRequest clearValuesRequest) throws IOException {
        final Clear clear = new Clear(s, s2, clearValuesRequest);
        this.this$1.this$0.initialize(clear);
        return clear;
    }
    
    public Get get(final String s, final String s2) throws IOException {
        final Get get = new Get(s, s2);
        this.this$1.this$0.initialize(get);
        return get;
    }
    
    public Update update(final String s, final String s2, final ValueRange valueRange) throws IOException {
        final Update update = new Update(s, s2, valueRange);
        this.this$1.this$0.initialize(update);
        return update;
    }
}
