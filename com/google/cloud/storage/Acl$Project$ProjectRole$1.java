package com.google.cloud.storage;

import com.google.api.core.ApiFunction;

static final class Acl$Project$ProjectRole$1 implements ApiFunction<String, ProjectRole> {
    Acl$Project$ProjectRole$1() {
        super();
    }
    
    public ProjectRole apply(final String s) {
        return new ProjectRole(s);
    }
    
    public /* bridge */ Object apply(final Object o) {
        return this.apply((String)o);
    }
}