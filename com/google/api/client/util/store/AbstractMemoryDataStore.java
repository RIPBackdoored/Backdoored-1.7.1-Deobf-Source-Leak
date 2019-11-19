package com.google.api.client.util.store;

import java.util.Arrays;
import com.google.api.client.util.Preconditions;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Lists;
import java.util.Collection;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import com.google.api.client.util.Maps;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.io.Serializable;

public class AbstractMemoryDataStore<V extends Serializable> extends AbstractDataStore<V>
{
    private final Lock lock;
    protected HashMap<String, byte[]> keyValueMap;
    
    protected AbstractMemoryDataStore(final DataStoreFactory dataStoreFactory, final String s) {
        super(dataStoreFactory, s);
        this.lock = new ReentrantLock();
        this.keyValueMap = Maps.<String, byte[]>newHashMap();
    }
    
    @Override
    public final Set<String> keySet() throws IOException {
        this.lock.lock();
        try {
            return Collections.<String>unmodifiableSet((Set<? extends String>)this.keyValueMap.keySet());
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public final Collection<V> values() throws IOException {
        this.lock.lock();
        try {
            final ArrayList<Object> arrayList = Lists.<Object>newArrayList();
            final Iterator<byte[]> iterator = this.keyValueMap.values().iterator();
            while (iterator.hasNext()) {
                arrayList.add(IOUtils.<Serializable>deserialize(iterator.next()));
            }
            return (Collection<V>)Collections.<Object>unmodifiableList((List<?>)arrayList);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public final V get(final String s) throws IOException {
        if (s == null) {
            return null;
        }
        this.lock.lock();
        try {
            return IOUtils.<V>deserialize(this.keyValueMap.get(s));
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public final DataStore<V> set(final String s, final V v) throws IOException {
        Preconditions.<String>checkNotNull(s);
        Preconditions.<V>checkNotNull(v);
        this.lock.lock();
        try {
            this.keyValueMap.put(s, IOUtils.serialize(v));
            this.save();
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    @Override
    public DataStore<V> delete(final String s) throws IOException {
        if (s == null) {
            return this;
        }
        this.lock.lock();
        try {
            this.keyValueMap.remove(s);
            this.save();
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    @Override
    public final DataStore<V> clear() throws IOException {
        this.lock.lock();
        try {
            this.keyValueMap.clear();
            this.save();
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    @Override
    public boolean containsKey(final String s) throws IOException {
        if (s == null) {
            return false;
        }
        this.lock.lock();
        try {
            return this.keyValueMap.containsKey(s);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public boolean containsValue(final V v) throws IOException {
        if (v == null) {
            return false;
        }
        this.lock.lock();
        try {
            final byte[] serialize = IOUtils.serialize(v);
            final Iterator<byte[]> iterator = this.keyValueMap.values().iterator();
            while (iterator.hasNext()) {
                if (Arrays.equals(serialize, iterator.next())) {
                    return true;
                }
            }
            return false;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public boolean isEmpty() throws IOException {
        this.lock.lock();
        try {
            return this.keyValueMap.isEmpty();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public int size() throws IOException {
        this.lock.lock();
        try {
            return this.keyValueMap.size();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void save() throws IOException {
    }
    
    @Override
    public String toString() {
        return DataStoreUtils.toString(this);
    }
}
