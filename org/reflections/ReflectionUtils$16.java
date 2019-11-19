package org.reflections;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import com.google.common.base.Predicate;

static final class ReflectionUtils$16 implements Predicate<Method> {
    final /* synthetic */ Class val$type;
    
    ReflectionUtils$16(final Class val$type) {
        this.val$type = val$type;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final Method method) {
        return method != null && this.val$type.isAssignableFrom(method.getReturnType());
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Method)o);
    }
}