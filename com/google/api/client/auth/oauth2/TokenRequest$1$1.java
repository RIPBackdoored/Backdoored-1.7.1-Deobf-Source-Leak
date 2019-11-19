package com.google.api.client.auth.oauth2;

import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpExecuteInterceptor;

class TokenRequest$1$1 implements HttpExecuteInterceptor {
    final /* synthetic */ HttpExecuteInterceptor val$interceptor;
    final /* synthetic */ TokenRequest$1 this$1;
    
    TokenRequest$1$1(final TokenRequest$1 this$1, final HttpExecuteInterceptor val$interceptor) {
        this.this$1 = this$1;
        this.val$interceptor = val$interceptor;
        super();
    }
    
    @Override
    public void intercept(final HttpRequest httpRequest) throws IOException {
        if (this.val$interceptor != null) {
            this.val$interceptor.intercept(httpRequest);
        }
        if (this.this$1.this$0.clientAuthentication != null) {
            this.this$1.this$0.clientAuthentication.intercept(httpRequest);
        }
    }
}