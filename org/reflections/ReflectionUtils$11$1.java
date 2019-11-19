package org.reflections;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import com.google.common.base.Predicate;

class ReflectionUtils$11$1 implements Predicate<Class<? extends Annotation>> {
    final /* synthetic */ ReflectionUtils$11 this$0;
    
    ReflectionUtils$11$1(final ReflectionUtils$11 this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final Class<? extends Annotation> clazz) {
        return clazz.equals(this.this$0.val$annotationClass);
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Class<? extends Annotation>)o);
    }
}