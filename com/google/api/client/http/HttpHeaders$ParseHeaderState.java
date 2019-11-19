package com.google.api.client.http;

import java.util.Arrays;
import java.lang.reflect.Type;
import java.util.List;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.ArrayValueMap;

private static final class ParseHeaderState
{
    final ArrayValueMap arrayValueMap;
    final StringBuilder logger;
    final ClassInfo classInfo;
    final List<Type> context;
    
    public ParseHeaderState(final HttpHeaders httpHeaders, final StringBuilder logger) {
        super();
        final Class<? extends HttpHeaders> class1 = httpHeaders.getClass();
        this.context = Arrays.<Type>asList(class1);
        this.classInfo = ClassInfo.of(class1, true);
        this.logger = logger;
        this.arrayValueMap = new ArrayValueMap(httpHeaders);
    }
    
    void finish() {
        this.arrayValueMap.setValues();
    }
}
