package com.google.api.client.util;

import java.util.Iterator;
import java.util.Map;
import java.util.AbstractSet;

final class EntrySet extends AbstractSet<Map.Entry<K, V>>
{
    final /* synthetic */ ArrayMap this$0;
    
    EntrySet(final ArrayMap this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return this.this$0.new EntryIterator();
    }
    
    @Override
    public int size() {
        return this.this$0.size;
    }
}
