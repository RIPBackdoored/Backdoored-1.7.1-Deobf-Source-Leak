package com.google.api.client.util.store;

import java.io.IOException;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Maps;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.Lock;

public abstract class AbstractDataStoreFactory implements DataStoreFactory
{
    private final Lock lock;
    private final Map<String, DataStore<? extends Serializable>> dataStoreMap;
    private static final Pattern ID_PATTERN;
    
    public AbstractDataStoreFactory() {
        super();
        this.lock = new ReentrantLock();
        this.dataStoreMap = (Map<String, DataStore<? extends Serializable>>)Maps.<Object, Object>newHashMap();
    }
    
    @Override
    public final <V extends Serializable> DataStore<V> getDataStore(final String s) throws IOException {
        Preconditions.checkArgument(AbstractDataStoreFactory.ID_PATTERN.matcher(s).matches(), "%s does not match pattern %s", s, AbstractDataStoreFactory.ID_PATTERN);
        this.lock.lock();
        try {
            DataStore<? extends Serializable> dataStore = this.dataStoreMap.get(s);
            if (dataStore == null) {
                dataStore = this.<Serializable>createDataStore(s);
                this.dataStoreMap.put(s, dataStore);
            }
            return (DataStore<V>)dataStore;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    protected abstract <V extends Serializable> DataStore<V> createDataStore(final String p0) throws IOException;
    
    static {
        ID_PATTERN = Pattern.compile("\\w{1,30}");
    }
}
