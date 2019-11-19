package com.google.api.client.repackaged.com.google.common.base;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Set;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class Present<T> extends Optional<T>
{
    private final T reference;
    private static final long serialVersionUID = 0L;
    
    Present(final T reference) {
        super();
        this.reference = reference;
    }
    
    @Override
    public boolean isPresent() {
        return true;
    }
    
    @Override
    public T get() {
        return this.reference;
    }
    
    @Override
    public T or(final T t) {
        Preconditions.<T>checkNotNull(t, (Object)"use Optional.orNull() instead of Optional.or(null)");
        return this.reference;
    }
    
    @Override
    public Optional<T> or(final Optional<? extends T> optional) {
        Preconditions.<Optional<? extends T>>checkNotNull(optional);
        return this;
    }
    
    @Override
    public T or(final Supplier<? extends T> supplier) {
        Preconditions.<Supplier<? extends T>>checkNotNull(supplier);
        return this.reference;
    }
    
    @Override
    public T orNull() {
        return this.reference;
    }
    
    @Override
    public Set<T> asSet() {
        return Collections.<T>singleton(this.reference);
    }
    
    @Override
    public <V> Optional<V> transform(final Function<? super T, V> function) {
        return new Present<V>(Preconditions.<V>checkNotNull(function.apply(this.reference), (Object)"the Function passed to Optional.transform() must not return null."));
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        return o instanceof Present && this.reference.equals(((Present)o).reference);
    }
    
    @Override
    public int hashCode() {
        return 1502476572 + this.reference.hashCode();
    }
    
    @Override
    public String toString() {
        return "Optional.of(" + this.reference + ")";
    }
}
