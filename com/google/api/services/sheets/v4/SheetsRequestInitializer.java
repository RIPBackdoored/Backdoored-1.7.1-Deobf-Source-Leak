package com.google.api.services.sheets.v4;

import java.io.IOException;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.googleapis.services.json.CommonGoogleJsonClientRequestInitializer;

public class SheetsRequestInitializer extends CommonGoogleJsonClientRequestInitializer
{
    public SheetsRequestInitializer() {
        super();
    }
    
    public SheetsRequestInitializer(final String s) {
        super(s);
    }
    
    public SheetsRequestInitializer(final String s, final String s2) {
        super(s, s2);
    }
    
    public final void initializeJsonRequest(final AbstractGoogleJsonClientRequest<?> abstractGoogleJsonClientRequest) throws IOException {
        super.initializeJsonRequest(abstractGoogleJsonClientRequest);
        this.initializeSheetsRequest((SheetsRequest<?>)abstractGoogleJsonClientRequest);
    }
    
    protected void initializeSheetsRequest(final SheetsRequest<?> sheetsRequest) throws IOException {
    }
}
