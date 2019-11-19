package org.reflections;

import java.lang.reflect.AnnotatedElement;
import java.lang.annotation.Annotation;
import javax.annotation.Nullable;
import com.google.common.base.Predicate;

static final class ReflectionUtils$4 implements Predicate<T> {
    final /* synthetic */ Class val$annotation;
    
    ReflectionUtils$4(final Class val$annotation) {
        this.val$annotation = val$annotation;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final T t) {
        return t != null && t.isAnnotationPresent(this.val$annotation);
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((AnnotatedElement)o);
    }
}