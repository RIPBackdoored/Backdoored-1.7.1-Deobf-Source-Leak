package org.reflections;

import javax.annotation.Nullable;
import com.google.common.base.Predicate;

class Reflections$2 implements Predicate<String> {
    final /* synthetic */ Reflections this$0;
    
    Reflections$2(final Reflections this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final String s) {
        final Class<?> forName = ReflectionUtils.forName(s, Reflections.access$000(this.this$0));
        return forName != null && !forName.isInterface();
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((String)o);
    }
}