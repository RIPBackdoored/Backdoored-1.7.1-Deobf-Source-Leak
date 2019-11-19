package org.reflections;

import java.lang.reflect.Member;
import javax.annotation.Nullable;
import com.google.common.base.Predicate;

static final class ReflectionUtils$1 implements Predicate<T> {
    final /* synthetic */ String val$name;
    
    ReflectionUtils$1(final String val$name) {
        this.val$name = val$name;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final T t) {
        return t != null && t.getName().equals(this.val$name);
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Member)o);
    }
}