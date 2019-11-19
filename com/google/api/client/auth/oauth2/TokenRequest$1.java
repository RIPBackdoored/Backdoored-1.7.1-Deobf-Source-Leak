package com.google.api.client.auth.oauth2;

import java.io.IOException;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;

class TokenRequest$1 implements HttpRequestInitializer {
    final /* synthetic */ TokenRequest this$0;
    
    TokenRequest$1(final TokenRequest this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public void initialize(final HttpRequest httpRequest) throws IOException {
        if (this.this$0.requestInitializer != null) {
            this.this$0.requestInitializer.initialize(httpRequest);
        }
        httpRequest.setInterceptor(new HttpExecuteInterceptor() {
            final /* synthetic */ HttpExecuteInterceptor val$interceptor = httpRequest.getInterceptor();
            final /* synthetic */ TokenRequest$1 this$1;
            
            TokenRequest$1$1() {
                this.this$1 = this$1;
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
        });
    }
}