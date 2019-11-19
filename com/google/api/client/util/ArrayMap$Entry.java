package com.google.api.client.util;

import java.util.Map;

final class Entry implements Map.Entry<K, V>
{
    private int index;
    final /* synthetic */ ArrayMap this$0;
    
    Entry(final ArrayMap this$0, final int index) {
        this.this$0 = this$0;
        super();
        this.index = index;
    }
    
    @Override
    public K getKey() {
        return this.this$0.getKey(this.index);
    }
    
    @Override
    public V getValue() {
        return this.this$0.getValue(this.index);
    }
    
    @Override
    public V setValue(final V v) {
        return this.this$0.set(this.index, v);
    }
    
    @Override
    public int hashCode() {
        final Object key = this.getKey();
        final Object value = this.getValue();
        return ((key != null) ? key.hashCode() : 0) ^ ((value != null) ? value.hashCode() : 0);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Map.Entry)) {
            return false;
        }
        final Map.Entry entry = (Map.Entry)o;
        return Objects.equal(this.getKey(), entry.getKey()) && Objects.equal(this.getValue(), entry.getValue());
    }
}
