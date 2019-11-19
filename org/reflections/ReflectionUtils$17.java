package org.reflections;

import java.lang.reflect.Member;
import javax.annotation.Nullable;
import com.google.common.base.Predicate;

static final class ReflectionUtils$17 implements Predicate<T> {
    final /* synthetic */ int val$mod;
    
    ReflectionUtils$17(final int val$mod) {
        this.val$mod = val$mod;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final T t) {
        return t != null && (t.getModifiers() & this.val$mod) != 0x0;
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Member)o);
    }
}