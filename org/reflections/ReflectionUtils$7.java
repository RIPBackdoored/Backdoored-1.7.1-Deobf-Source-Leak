package org.reflections;

import java.lang.reflect.AnnotatedElement;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import com.google.common.base.Predicate;

static final class ReflectionUtils$7 implements Predicate<T> {
    final /* synthetic */ Annotation[] val$annotations;
    
    ReflectionUtils$7(final Annotation[] val$annotations) {
        this.val$annotations = val$annotations;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final T t) {
        if (t != null) {
            final Annotation[] annotations = t.getAnnotations();
            if (annotations.length == this.val$annotations.length) {
                for (int i = 0; i < annotations.length; ++i) {
                    if (!ReflectionUtils.access$100(annotations[i], this.val$annotations[i])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((AnnotatedElement)o);
    }
}