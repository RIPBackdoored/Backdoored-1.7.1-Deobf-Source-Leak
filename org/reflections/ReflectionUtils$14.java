package org.reflections;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import com.google.common.base.Predicate;

static final class ReflectionUtils$14 implements Predicate<Field> {
    final /* synthetic */ Class val$type;
    
    ReflectionUtils$14(final Class val$type) {
        this.val$type = val$type;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final Field field) {
        return field != null && this.val$type.isAssignableFrom(field.getType());
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Field)o);
    }
}