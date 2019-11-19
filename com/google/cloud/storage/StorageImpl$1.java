package com.google.cloud.storage;

import com.google.cloud.Tuple;
import com.google.common.base.Function;

static final class StorageImpl$1 implements Function<Tuple<Storage, Boolean>, Boolean> {
    StorageImpl$1() {
        super();
    }
    
    @Override
    public Boolean apply(final Tuple<Storage, Boolean> tuple) {
        return (Boolean)tuple.y();
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((Tuple<Storage, Boolean>)o);
    }
}