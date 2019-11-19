package com.google.api.client.util.store;

import java.io.IOException;
import java.io.Serializable;

public class MemoryDataStoreFactory extends AbstractDataStoreFactory
{
    public MemoryDataStoreFactory() {
        super();
    }
    
    @Override
    protected <V extends Serializable> DataStore<V> createDataStore(final String s) throws IOException {
        return new MemoryDataStore<V>(this, s);
    }
    
    public static MemoryDataStoreFactory getDefaultInstance() {
        return InstanceHolder.INSTANCE;
    }
}
