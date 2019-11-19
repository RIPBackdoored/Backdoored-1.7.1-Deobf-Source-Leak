package com.google.api.client.util;

import java.util.Iterator;
import java.util.Map;
import java.util.AbstractSet;

final class EntrySet extends AbstractSet<Map.Entry<String, Object>>
{
    final /* synthetic */ DataMap this$0;
    
    EntrySet(final DataMap this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public EntryIterator iterator() {
        return this.this$0.new EntryIterator();
    }
    
    @Override
    public int size() {
        int n = 0;
        final Iterator<String> iterator = this.this$0.classInfo.names.iterator();
        while (iterator.hasNext()) {
            if (this.this$0.classInfo.getFieldInfo(iterator.next()).getValue(this.this$0.object) != null) {
                ++n;
            }
        }
        return n;
    }
    
    @Override
    public void clear() {
        final Iterator<String> iterator = this.this$0.classInfo.names.iterator();
        while (iterator.hasNext()) {
            this.this$0.classInfo.getFieldInfo(iterator.next()).setValue(this.this$0.object, null);
        }
    }
    
    @Override
    public boolean isEmpty() {
        final Iterator<String> iterator = this.this$0.classInfo.names.iterator();
        while (iterator.hasNext()) {
            if (this.this$0.classInfo.getFieldInfo(iterator.next()).getValue(this.this$0.object) != null) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public /* bridge */ Iterator iterator() {
        return this.iterator();
    }
}
