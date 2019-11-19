package org.reflections;

import java.util.Map;
import com.google.common.collect.Sets;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;
import com.google.common.base.Supplier;

class Store$1 implements Supplier<Set<String>> {
    final /* synthetic */ Store this$0;
    
    Store$1(final Store this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Set<String> get() {
        return (Set<String>)Sets.newSetFromMap((Map)new ConcurrentHashMap());
    }
    
    @Override
    public /* bridge */ Object get() {
        return this.get();
    }
}