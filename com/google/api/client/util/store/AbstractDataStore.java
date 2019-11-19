package com.google.api.client.util.store;

import java.io.IOException;
import com.google.api.client.util.Preconditions;
import java.io.Serializable;

public abstract class AbstractDataStore<V extends Serializable> implements DataStore<V>
{
    private final DataStoreFactory dataStoreFactory;
    private final String id;
    
    protected AbstractDataStore(final DataStoreFactory dataStoreFactory, final String s) {
        super();
        this.dataStoreFactory = Preconditions.<DataStoreFactory>checkNotNull(dataStoreFactory);
        this.id = Preconditions.<String>checkNotNull(s);
    }
    
    @Override
    public DataStoreFactory getDataStoreFactory() {
        return this.dataStoreFactory;
    }
    
    @Override
    public final String getId() {
        return this.id;
    }
    
    @Override
    public boolean containsKey(final String s) throws IOException {
        return this.get(s) != null;
    }
    
    @Override
    public boolean containsValue(final V v) throws IOException {
        return this.values().contains(v);
    }
    
    @Override
    public boolean isEmpty() throws IOException {
        return this.size() == 0;
    }
    
    @Override
    public int size() throws IOException {
        return this.keySet().size();
    }
}
