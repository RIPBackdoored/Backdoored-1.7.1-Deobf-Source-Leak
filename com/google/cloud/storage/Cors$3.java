package com.google.cloud.storage;

import com.google.common.base.Function;

static final class Cors$3 implements Function<String, HttpMethod> {
    Cors$3() {
        super();
    }
    
    @Override
    public HttpMethod apply(final String s) {
        return HttpMethod.valueOf(s.toUpperCase());
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((String)o);
    }
}