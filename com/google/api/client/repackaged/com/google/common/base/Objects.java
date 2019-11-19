package com.google.api.client.repackaged.com.google.common.base;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import javax.annotation.Nullable;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class Objects extends ExtraObjectsMethodsForWeb
{
    private Objects() {
        super();
    }
    
    public static boolean equal(@Nullable final Object o, @Nullable final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static int hashCode(@Nullable final Object... array) {
        return Arrays.hashCode(array);
    }
    
    @Deprecated
    public static ToStringHelper toStringHelper(final Object o) {
        return new ToStringHelper(o.getClass().getSimpleName());
    }
    
    @Deprecated
    public static ToStringHelper toStringHelper(final Class<?> clazz) {
        return new ToStringHelper(clazz.getSimpleName());
    }
    
    @Deprecated
    public static ToStringHelper toStringHelper(final String s) {
        return new ToStringHelper(s);
    }
    
    @Deprecated
    public static <T> T firstNonNull(@Nullable final T t, @Nullable final T t2) {
        return MoreObjects.<T>firstNonNull(t, t2);
    }
}
