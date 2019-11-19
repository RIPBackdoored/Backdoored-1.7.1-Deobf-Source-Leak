package org.reflections;

import java.lang.reflect.AnnotatedElement;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import com.google.common.base.Predicate;

static final class ReflectionUtils$3 implements Predicate<T> {
    final /* synthetic */ String val$regex;
    
    ReflectionUtils$3(final String val$regex) {
        this.val$regex = val$regex;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final T t) {
        return Pattern.matches(this.val$regex, t.toString());
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((AnnotatedElement)o);
    }
}