package com.google.api.client.googleapis.services.json;

import java.io.IOException;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer;

public class CommonGoogleJsonClientRequestInitializer extends CommonGoogleClientRequestInitializer
{
    public CommonGoogleJsonClientRequestInitializer() {
        super();
    }
    
    public CommonGoogleJsonClientRequestInitializer(final String s) {
        super(s);
    }
    
    public CommonGoogleJsonClientRequestInitializer(final String s, final String s2) {
        super(s, s2);
    }
    
    @Override
    public final void initialize(final AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
        super.initialize(abstractGoogleClientRequest);
        this.initializeJsonRequest((AbstractGoogleJsonClientRequest<?>)abstractGoogleClientRequest);
    }
    
    protected void initializeJsonRequest(final AbstractGoogleJsonClientRequest<?> abstractGoogleJsonClientRequest) throws IOException {
    }
}
