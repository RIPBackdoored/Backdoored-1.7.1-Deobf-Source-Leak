package com.google.api.client.http;

static final class HttpBackOffUnsuccessfulResponseHandler$BackOffRequired$2 implements BackOffRequired {
    HttpBackOffUnsuccessfulResponseHandler$BackOffRequired$2() {
        super();
    }
    
    @Override
    public boolean isRequired(final HttpResponse httpResponse) {
        return httpResponse.getStatusCode() / 100 == 5;
    }
}