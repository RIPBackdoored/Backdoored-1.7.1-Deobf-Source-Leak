package com.google.api.client.testing.http;

import java.io.IOException;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.Beta;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;

@Beta
public class MockHttpUnsuccessfulResponseHandler implements HttpUnsuccessfulResponseHandler
{
    private boolean isCalled;
    private boolean successfullyHandleResponse;
    
    public MockHttpUnsuccessfulResponseHandler(final boolean successfullyHandleResponse) {
        super();
        this.successfullyHandleResponse = successfullyHandleResponse;
    }
    
    public boolean isCalled() {
        return this.isCalled;
    }
    
    @Override
    public boolean handleResponse(final HttpRequest httpRequest, final HttpResponse httpResponse, final boolean b) throws IOException {
        this.isCalled = true;
        return this.successfullyHandleResponse;
    }
}
