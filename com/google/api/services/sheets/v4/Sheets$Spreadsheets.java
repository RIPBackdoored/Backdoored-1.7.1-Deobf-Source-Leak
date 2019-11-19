package com.google.api.services.sheets.v4;

import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ClearValuesResponse;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesByDataFilterResponse;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.BatchGetValuesByDataFilterResponse;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.BatchClearValuesByDataFilterResponse;
import com.google.api.services.sheets.v4.model.BatchClearValuesResponse;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesByDataFilterRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchGetValuesByDataFilterRequest;
import com.google.api.services.sheets.v4.model.BatchClearValuesByDataFilterRequest;
import com.google.api.services.sheets.v4.model.BatchClearValuesRequest;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.CopySheetToAnotherSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.SearchDeveloperMetadataResponse;
import com.google.api.services.sheets.v4.model.DeveloperMetadata;
import com.google.api.services.sheets.v4.model.SearchDeveloperMetadataRequest;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import java.util.List;
import com.google.api.client.util.GenericData;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Key;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.GetSpreadsheetByDataFilterRequest;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import java.io.IOException;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;

public class Spreadsheets
{
    final /* synthetic */ Sheets this$0;
    
    public Spreadsheets(final Sheets this$0) {
        this.this$0 = this$0;
        super();
    }
    
    public BatchUpdate batchUpdate(final String s, final BatchUpdateSpreadsheetRequest batchUpdateSpreadsheetRequest) throws IOException {
        final BatchUpdate batchUpdate = new BatchUpdate(s, batchUpdateSpreadsheetRequest);
        this.this$0.initialize(batchUpdate);
        return batchUpdate;
    }
    
    public Create create(final Spreadsheet spreadsheet) throws IOException {
        final Create create = new Create(spreadsheet);
        this.this$0.initialize(create);
        return create;
    }
    
    public Get get(final String s) throws IOException {
        final Get get = new Get(s);
        this.this$0.initialize(get);
        return get;
    }
    
    public GetByDataFilter getByDataFilter(final String s, final GetSpreadsheetByDataFilterRequest getSpreadsheetByDataFilterRequest) throws IOException {
        final GetByDataFilter getByDataFilter = new GetByDataFilter(s, getSpreadsheetByDataFilterRequest);
        this.this$0.initialize(getByDataFilter);
        return getByDataFilter;
    }
    
    public DeveloperMetadata developerMetadata() {
        return new DeveloperMetadata();
    }
    
    public SheetsOperations sheets() {
        return new SheetsOperations();
    }
    
    public Values values() {
        return new Values();
    }
}
