package com.google.cloud.storage;

import com.google.api.services.storage.model.Bucket;
import com.google.common.base.Function;

static final class Cors$1 implements Function<Bucket.Cors, Cors> {
    Cors$1() {
        super();
    }
    
    @Override
    public Cors apply(final Bucket.Cors cors) {
        return Cors.fromPb(cors);
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((Bucket.Cors)o);
    }
}