package org.reflections.serializers;

import com.google.common.collect.Sets;
import java.util.Set;
import com.google.common.base.Supplier;

class JsonSerializer$1$1 implements Supplier<Set<String>> {
    final /* synthetic */ JsonSerializer$1 this$1;
    
    JsonSerializer$1$1(final JsonSerializer$1 this$1) {
        this.this$1 = this$1;
        super();
    }
    
    @Override
    public Set<String> get() {
        return (Set<String>)Sets.<Object>newHashSet();
    }
    
    @Override
    public /* bridge */ Object get() {
        return this.get();
    }
}