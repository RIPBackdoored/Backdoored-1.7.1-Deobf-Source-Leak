package com.google.api.client.util.store;

import java.io.Serializable;

static class MemoryDataStore<V extends Serializable> extends AbstractMemoryDataStore<V>
{
    MemoryDataStore(final MemoryDataStoreFactory memoryDataStoreFactory, final String s) {
        super(memoryDataStoreFactory, s);
    }
    
    @Override
    public MemoryDataStoreFactory getDataStoreFactory() {
        return (MemoryDataStoreFactory)super.getDataStoreFactory();
    }
    
    @Override
    public /* bridge */ DataStoreFactory getDataStoreFactory() {
        return this.getDataStoreFactory();
    }
}
