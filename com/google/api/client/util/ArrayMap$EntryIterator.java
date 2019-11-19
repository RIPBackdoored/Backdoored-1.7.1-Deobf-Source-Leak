package com.google.api.client.util;

import java.util.NoSuchElementException;
import java.util.Map;
import java.util.Iterator;

final class EntryIterator implements Iterator<Map.Entry<K, V>>
{
    private boolean removed;
    private int nextIndex;
    final /* synthetic */ ArrayMap this$0;
    
    EntryIterator(final ArrayMap this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public boolean hasNext() {
        return this.nextIndex < this.this$0.size;
    }
    
    @Override
    public Map.Entry<K, V> next() {
        final int nextIndex = this.nextIndex;
        if (nextIndex == this.this$0.size) {
            throw new NoSuchElementException();
        }
        ++this.nextIndex;
        return this.this$0.new Entry(nextIndex);
    }
    
    @Override
    public void remove() {
        final int n = this.nextIndex - 1;
        if (this.removed || n < 0) {
            throw new IllegalArgumentException();
        }
        this.this$0.remove(n);
        this.removed = true;
    }
    
    @Override
    public /* bridge */ Object next() {
        return this.next();
    }
}
