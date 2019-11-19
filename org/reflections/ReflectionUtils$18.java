package org.reflections;

import javax.annotation.Nullable;
import com.google.common.base.Predicate;

static final class ReflectionUtils$18 implements Predicate<Class<?>> {
    final /* synthetic */ int val$mod;
    
    ReflectionUtils$18(final int val$mod) {
        this.val$mod = val$mod;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final Class<?> clazz) {
        return clazz != null && (clazz.getModifiers() & this.val$mod) != 0x0;
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Class<?>)o);
    }
}