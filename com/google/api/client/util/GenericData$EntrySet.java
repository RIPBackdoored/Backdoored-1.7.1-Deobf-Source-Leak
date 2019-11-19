package com.google.api.client.util;

import java.util.Iterator;
import java.util.Map;
import java.util.AbstractSet;

final class EntrySet extends AbstractSet<Map.Entry<String, Object>>
{
    private final DataMap.EntrySet dataEntrySet;
    final /* synthetic */ GenericData this$0;
    
    EntrySet(final GenericData this$0) {
        this.this$0 = this$0;
        super();
        this.dataEntrySet = new DataMap(this$0, this$0.classInfo.getIgnoreCase()).entrySet();
    }
    
    @Override
    public Iterator<Map.Entry<String, Object>> iterator() {
        return this.this$0.new EntryIterator(this.dataEntrySet);
    }
    
    @Override
    public int size() {
        return this.this$0.unknownFields.size() + this.dataEntrySet.size();
    }
    
    @Override
    public void clear() {
        this.this$0.unknownFields.clear();
        this.dataEntrySet.clear();
    }
}
