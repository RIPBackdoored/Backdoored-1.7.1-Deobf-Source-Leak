package com.google.api.services.sheets.v4;

import com.google.api.client.util.GenericData;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Key;
import com.google.api.services.sheets.v4.model.SheetProperties;
import java.io.IOException;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.services.sheets.v4.model.CopySheetToAnotherSpreadsheetRequest;

public class SheetsOperations
{
    final /* synthetic */ Spreadsheets this$1;
    
    public SheetsOperations(final Spreadsheets this$1) {
        this.this$1 = this$1;
        super();
    }
    
    public CopyTo copyTo(final String s, final Integer n, final CopySheetToAnotherSpreadsheetRequest copySheetToAnotherSpreadsheetRequest) throws IOException {
        final CopyTo copyTo = new CopyTo(s, n, copySheetToAnotherSpreadsheetRequest);
        this.this$1.this$0.initialize(copyTo);
        return copyTo;
    }
}
