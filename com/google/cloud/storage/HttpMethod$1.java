package com.google.cloud.storage;

import com.google.api.core.ApiFunction;

static final class HttpMethod$1 implements ApiFunction<String, HttpMethod> {
    HttpMethod$1() {
        super();
    }
    
    public HttpMethod apply(final String s) {
        return new HttpMethod(s, null);
    }
    
    public /* bridge */ Object apply(final Object o) {
        return this.apply((String)o);
    }
}