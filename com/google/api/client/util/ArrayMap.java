package com.google.api.client.util;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.AbstractSet;
import java.util.Map;
import java.util.Set;
import java.util.AbstractMap;

public class ArrayMap<K, V> extends AbstractMap<K, V> implements Cloneable
{
    int size;
    private Object[] data;
    
    public ArrayMap() {
        super();
    }
    
    public static <K, V> ArrayMap<K, V> create() {
        return new ArrayMap<K, V>();
    }
    
    public static <K, V> ArrayMap<K, V> create(final int n) {
        final ArrayMap<K, V> create = (ArrayMap<K, V>)ArrayMap.<Object, Object>create();
        create.ensureCapacity(n);
        return create;
    }
    
    public static <K, V> ArrayMap<K, V> of(final Object... array) {
        final ArrayMap<K, V> create = ArrayMap.<K, V>create(1);
        final int length = array.length;
        if (1 == length % 2) {
            throw new IllegalArgumentException("missing value for last key: " + array[length - 1]);
        }
        create.size = array.length / 2;
        System.arraycopy(array, 0, create.data = new Object[length], 0, length);
        return create;
    }
    
    @Override
    public final int size() {
        return this.size;
    }
    
    public final K getKey(final int n) {
        if (n < 0 || n >= this.size) {
            return null;
        }
        return (K)this.data[n << 1];
    }
    
    public final V getValue(final int n) {
        if (n < 0 || n >= this.size) {
            return null;
        }
        return this.valueAtDataIndex(1 + (n << 1));
    }
    
    public final V set(final int n, final K k, final V v) {
        if (n < 0) {
            throw new IndexOutOfBoundsException();
        }
        final int size = n + 1;
        this.ensureCapacity(size);
        final int n2 = n << 1;
        final V valueAtDataIndex = this.valueAtDataIndex(n2 + 1);
        this.setData(n2, k, v);
        if (size > this.size) {
            this.size = size;
        }
        return valueAtDataIndex;
    }
    
    public final V set(final int n, final V v) {
        final int size = this.size;
        if (n < 0 || n >= size) {
            throw new IndexOutOfBoundsException();
        }
        final int n2 = 1 + (n << 1);
        final V valueAtDataIndex = this.valueAtDataIndex(n2);
        this.data[n2] = v;
        return valueAtDataIndex;
    }
    
    public final void add(final K k, final V v) {
        this.set(this.size, k, v);
    }
    
    public final V remove(final int n) {
        return this.removeFromDataIndexOfKey(n << 1);
    }
    
    @Override
    public final boolean containsKey(final Object o) {
        return -2 != this.getDataIndexOfKey(o);
    }
    
    public final int getIndexOfKey(final K k) {
        return this.getDataIndexOfKey(k) >> 1;
    }
    
    @Override
    public final V get(final Object o) {
        return this.valueAtDataIndex(this.getDataIndexOfKey(o) + 1);
    }
    
    @Override
    public final V put(final K k, final V v) {
        int n = this.getIndexOfKey(k);
        if (n == -1) {
            n = this.size;
        }
        return this.set(n, k, v);
    }
    
    @Override
    public final V remove(final Object o) {
        return this.removeFromDataIndexOfKey(this.getDataIndexOfKey(o));
    }
    
    public final void trim() {
        this.setDataCapacity(this.size << 1);
    }
    
    public final void ensureCapacity(final int n) {
        if (n < 0) {
            throw new IndexOutOfBoundsException();
        }
        final Object[] data = this.data;
        final int n2 = n << 1;
        final int n3 = (data == null) ? 0 : data.length;
        if (n2 > n3) {
            int dataCapacity = n3 / 2 * 3 + 1;
            if (dataCapacity % 2 != 0) {
                ++dataCapacity;
            }
            if (dataCapacity < n2) {
                dataCapacity = n2;
            }
            this.setDataCapacity(dataCapacity);
        }
    }
    
    private void setDataCapacity(final int n) {
        this.data = null;
    }
    
    private void setData(final int n, final K k, final V v) {
        final Object[] data = this.data;
        data[n] = k;
        data[n + 1] = v;
    }
    
    private V valueAtDataIndex(final int n) {
        if (n < 0) {
            return null;
        }
        return (V)this.data[n];
    }
    
    private int getDataIndexOfKey(final Object o) {
        final int n = this.size << 1;
        final Object[] data = this.data;
        for (int i = 0; i < n; i += 2) {
            final Object o2 = data[i];
            if (o == null) {
                if (o2 == null) {
                    return i;
                }
            }
            else if (o.equals(o2)) {
                return i;
            }
        }
        return -2;
    }
    
    private V removeFromDataIndexOfKey(final int n) {
        final int n2 = this.size << 1;
        if (n < 0 || n >= n2) {
            return null;
        }
        final V valueAtDataIndex = this.valueAtDataIndex(n + 1);
        final Object[] data = this.data;
        final int n3 = n2 - n - 2;
        if (n3 != 0) {
            System.arraycopy(data, n + 2, data, n, n3);
        }
        --this.size;
        this.setData(n2 - 2, null, null);
        return valueAtDataIndex;
    }
    
    @Override
    public void clear() {
        this.size = 0;
        this.data = null;
    }
    
    @Override
    public final boolean containsValue(final Object o) {
        final int n = this.size << 1;
        final Object[] data = this.data;
        for (int i = 1; i < n; i += 2) {
            final Object o2 = data[i];
            if (o == null) {
                if (o2 == null) {
                    return true;
                }
            }
            else if (o.equals(o2)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public final Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }
    
    public ArrayMap<K, V> clone() {
        try {
            final ArrayMap arrayMap = (ArrayMap)super.clone();
            final Object[] data = this.data;
            if (data != null) {
                final int length = data.length;
                System.arraycopy(data, 0, arrayMap.data = new Object[length], 0, length);
            }
            return arrayMap;
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public /* bridge */ Object clone() throws CloneNotSupportedException {
        return this.clone();
    }
}
