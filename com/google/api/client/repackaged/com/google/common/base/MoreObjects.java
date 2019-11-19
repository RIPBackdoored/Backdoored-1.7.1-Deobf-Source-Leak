package com.google.api.client.repackaged.com.google.common.base;

import java.util.Arrays;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javax.annotation.Nullable;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class MoreObjects
{
    public static <T> T firstNonNull(@Nullable final T t, @Nullable final T t2) {
        return (t != null) ? t : Preconditions.<T>checkNotNull(t2);
    }
    
    public static ToStringHelper toStringHelper(final Object o) {
        return new ToStringHelper(o.getClass().getSimpleName());
    }
    
    public static ToStringHelper toStringHelper(final Class<?> clazz) {
        return new ToStringHelper(clazz.getSimpleName());
    }
    
    public static ToStringHelper toStringHelper(final String s) {
        return new ToStringHelper(s);
    }
    
    private MoreObjects() {
        super();
    }
}
