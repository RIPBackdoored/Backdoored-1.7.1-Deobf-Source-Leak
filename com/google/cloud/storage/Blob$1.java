package com.google.cloud.storage;

import com.google.api.services.storage.model.StorageObject;
import com.google.cloud.Tuple;
import com.google.common.base.Function;

static final class Blob$1 implements Function<Tuple<Storage, StorageObject>, Blob> {
    Blob$1() {
        super();
    }
    
    @Override
    public Blob apply(final Tuple<Storage, StorageObject> tuple) {
        return Blob.fromPb((Storage)tuple.x(), (StorageObject)tuple.y());
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((Tuple<Storage, StorageObject>)o);
    }
}