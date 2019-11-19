package com.google.api.client.repackaged.com.google.common.base;

import java.util.Collections;
import java.util.Set;
import javax.annotation.Nullable;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class Absent<T> extends Optional<T>
{
    static final Absent<Object> INSTANCE;
    private static final long serialVersionUID = 0L;
    
    static <T> Optional<T> withType() {
        return (Optional<T>)Absent.INSTANCE;
    }
    
    private Absent() {
        super();
    }
    
    @Override
    public boolean isPresent() {
        return false;
    }
    
    @Override
    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }
    
    @Override
    public T or(final T t) {
        return Preconditions.<T>checkNotNull(t, (Object)"use Optional.orNull() instead of Optional.or(null)");
    }
    
    @Override
    public Optional<T> or(final Optional<? extends T> optional) {
        return Preconditions.<Optional<T>>checkNotNull((Optional<T>)optional);
    }
    
    @Override
    public T or(final Supplier<? extends T> supplier) {
        return Preconditions.<T>checkNotNull((T)supplier.get(), (Object)"use Optional.orNull() instead of a Supplier that returns null");
    }
    
    @Nullable
    @Override
    public T orNull() {
        return null;
    }
    
    @Override
    public Set<T> asSet() {
        return Collections.<T>emptySet();
    }
    
    @Override
    public <V> Optional<V> transform(final Function<? super T, V> function) {
        Preconditions.<Function<? super T, V>>checkNotNull(function);
        return Optional.<V>absent();
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        return o == this;
    }
    
    @Override
    public int hashCode() {
        return 2040732332;
    }
    
    @Override
    public String toString() {
        return "Optional.absent()";
    }
    
    private Object readResolve() {
        return Absent.INSTANCE;
    }
    
    static {
        INSTANCE = new Absent<Object>();
    }
}
