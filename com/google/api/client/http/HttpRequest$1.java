package com.google.api.client.http;

import java.util.concurrent.Callable;

class HttpRequest$1 implements Callable<HttpResponse> {
    final /* synthetic */ HttpRequest this$0;
    
    HttpRequest$1(final HttpRequest this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public HttpResponse call() throws Exception {
        return this.this$0.execute();
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}