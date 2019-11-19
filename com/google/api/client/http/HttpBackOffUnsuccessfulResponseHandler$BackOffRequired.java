package com.google.api.client.http;

import com.google.api.client.util.Beta;

@Beta
public interface BackOffRequired
{
    public static final BackOffRequired ALWAYS = new BackOffRequired() {
        HttpBackOffUnsuccessfulResponseHandler$BackOffRequired$1() {
            super();
        }
        
        @Override
        public boolean isRequired(final HttpResponse httpResponse) {
            return true;
        }
    };
    public static final BackOffRequired ON_SERVER_ERROR = new BackOffRequired() {
        HttpBackOffUnsuccessfulResponseHandler$BackOffRequired$2() {
            super();
        }
        
        @Override
        public boolean isRequired(final HttpResponse httpResponse) {
            return httpResponse.getStatusCode() / 100 == 5;
        }
    };
    
    boolean isRequired(final HttpResponse p0);
}
