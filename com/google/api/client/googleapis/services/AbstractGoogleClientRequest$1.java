package com.google.api.client.googleapis.services;

import java.io.IOException;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponseInterceptor;

class AbstractGoogleClientRequest$1 implements HttpResponseInterceptor {
    final /* synthetic */ HttpResponseInterceptor val$responseInterceptor;
    final /* synthetic */ HttpRequest val$httpRequest;
    final /* synthetic */ AbstractGoogleClientRequest this$0;
    
    AbstractGoogleClientRequest$1(final AbstractGoogleClientRequest this$0, final HttpResponseInterceptor val$responseInterceptor, final HttpRequest val$httpRequest) {
        this.this$0 = this$0;
        this.val$responseInterceptor = val$responseInterceptor;
        this.val$httpRequest = val$httpRequest;
        super();
    }
    
    public void interceptResponse(final HttpResponse httpResponse) throws IOException {
        if (this.val$responseInterceptor != null) {
            this.val$responseInterceptor.interceptResponse(httpResponse);
        }
        if (!httpResponse.isSuccessStatusCode() && this.val$httpRequest.getThrowExceptionOnExecuteError()) {
            throw this.this$0.newExceptionOnError(httpResponse);
        }
    }
}