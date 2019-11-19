package com.google.api.client.googleapis.testing.json;

import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Beta;

@Beta
public final class GoogleJsonResponseExceptionFactoryTesting
{
    public GoogleJsonResponseExceptionFactoryTesting() {
        super();
    }
    
    public static GoogleJsonResponseException newMock(final JsonFactory jsonFactory, final int statusCode, final String reasonPhrase) throws IOException {
        final HttpRequest buildGetRequest = new MockHttpTransport.Builder().setLowLevelHttpResponse(new MockLowLevelHttpResponse().setStatusCode(statusCode).setReasonPhrase(reasonPhrase)).build().createRequestFactory().buildGetRequest(HttpTesting.SIMPLE_GENERIC_URL);
        buildGetRequest.setThrowExceptionOnExecuteError(false);
        return GoogleJsonResponseException.from(jsonFactory, buildGetRequest.execute());
    }
}
