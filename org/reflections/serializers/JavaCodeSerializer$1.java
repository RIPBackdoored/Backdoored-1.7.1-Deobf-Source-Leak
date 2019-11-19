package org.reflections.serializers;

import com.google.common.collect.Sets;
import java.util.Set;
import com.google.common.base.Supplier;

class JavaCodeSerializer$1 implements Supplier<Set<String>> {
    final /* synthetic */ JavaCodeSerializer this$0;
    
    JavaCodeSerializer$1(final JavaCodeSerializer this$0) {
        this.this$0 = this$0;
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