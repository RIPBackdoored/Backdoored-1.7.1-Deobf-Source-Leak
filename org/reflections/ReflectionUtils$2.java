package org.reflections;

import java.lang.reflect.Member;
import javax.annotation.Nullable;
import com.google.common.base.Predicate;

static final class ReflectionUtils$2 implements Predicate<T> {
    final /* synthetic */ String val$prefix;
    
    ReflectionUtils$2(final String val$prefix) {
        this.val$prefix = val$prefix;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final T t) {
        return t != null && t.getName().startsWith(this.val$prefix);
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Member)o);
    }
}