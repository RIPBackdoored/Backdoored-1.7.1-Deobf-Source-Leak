package org.reflections;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import javax.annotation.Nullable;
import com.google.common.base.Predicate;

static final class ReflectionUtils$5 implements Predicate<T> {
    final /* synthetic */ Class[] val$annotations;
    
    ReflectionUtils$5(final Class[] val$annotations) {
        this.val$annotations = val$annotations;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final T t) {
        return t != null && Arrays.equals(this.val$annotations, ReflectionUtils.access$000(t.getAnnotations()));
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((AnnotatedElement)o);
    }
}