package org.reflections;

import java.lang.reflect.AnnotatedElement;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import com.google.common.base.Predicate;

static final class ReflectionUtils$6 implements Predicate<T> {
    final /* synthetic */ Annotation val$annotation;
    
    ReflectionUtils$6(final Annotation val$annotation) {
        this.val$annotation = val$annotation;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final T t) {
        return t != null && t.isAnnotationPresent(this.val$annotation.annotationType()) && ReflectionUtils.access$100(t.<Annotation>getAnnotation(this.val$annotation.annotationType()), this.val$annotation);
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((AnnotatedElement)o);
    }
}